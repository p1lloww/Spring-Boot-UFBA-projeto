package com.tomorrowproject.restaurante_api.services;

import com.tomorrowproject.restaurante_api.DTO.prato.PratoDTO;
import com.tomorrowproject.restaurante_api.Mapper.ObjectMapper;
import com.tomorrowproject.restaurante_api.entity.Cliente;
import com.tomorrowproject.restaurante_api.entity.Prato;
import com.tomorrowproject.restaurante_api.exception.NotFoundException;
import com.tomorrowproject.restaurante_api.repository.CategoriaRepository;
import com.tomorrowproject.restaurante_api.repository.PratoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PratoService {
    private static final Logger log = LoggerFactory.getLogger(PratoService.class);

    @Autowired
    private PratoRepository pratoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public List<PratoDTO> buscarTodosOsPratos() {
        log.info("Iniciando busca de todos os pratos ordenados por nome");

        try {
            List<Prato> pratos = pratoRepository.findAllByOrderByNomeAsc();
            log.debug("Encontrados {} pratos na base de dados", pratos.size());

            List<PratoDTO> pratosDTO = ObjectMapper.parseListObjects(pratos, PratoDTO.class);

            if (pratos.isEmpty()) {
                log.warn("Nenhum prato encontrado na base de dados");
            } else {
                log.info("Busca de pratos concluída com sucesso. Total: {}", pratosDTO.size());
            }

            return pratosDTO;

        } catch (Exception e) {
            log.error("Erro ao buscar todos os pratos: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public PratoDTO buscarPratoPorID(Long id) {
        log.info("Iniciando busca do prato com ID: {}", id);

        try {
            Prato prato = pratoRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Prato não encontrado com ID: {}", id);
                        return new NotFoundException("Falha ao Identificar o id do prato");
                    }
            );

            log.debug("Prato encontrado: {} (ID: {}), Preço: {}, Tempo de Preparo: {}",
                    prato.getNome(), id, prato.getPreco(), prato.getTempoDePreparo());

            PratoDTO pratoDTO = ObjectMapper.parseObject(prato, PratoDTO.class);

            log.info("Busca do prato concluída com sucesso para ID: {}", id);
            return pratoDTO;

        } catch (NotFoundException e) {
            log.error("Prato não encontrado com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao buscar prato com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public PratoDTO criarPrato(PratoDTO pratoDTO) {
        log.info("Iniciando criação de novo prato: {}", pratoDTO.getNome());
        log.debug("Dados do prato: Nome={}, Preço={}, Tempo de Preparo={}, Descrição={}",
                pratoDTO.getNome(), pratoDTO.getPreco(), pratoDTO.getTempoDePreparo(), pratoDTO.getDescricao());

        try {
            Prato prato = ObjectMapper.parseObject(pratoDTO, Prato.class);
            Prato pratoSalvo = pratoRepository.save(prato);

            log.info("Prato criado com sucesso. ID gerado: {}, Nome: {}, Preço: {}",
                    pratoSalvo.getId(), pratoSalvo.getNome(), pratoSalvo.getPreco());

            PratoDTO pratoDTOSalvo = ObjectMapper.parseObject(pratoSalvo, PratoDTO.class);
            return pratoDTOSalvo;

        } catch (Exception e) {
            log.error("Erro ao criar prato '{}': {}", pratoDTO.getNome(), e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public PratoDTO atualizarPrato(Long id, PratoDTO pratoDTO) {
        log.info("Iniciando atualização do prato com ID: {}", id);
        log.debug("Novos dados: Nome={}, Preço={}, Tempo de Preparo={}, Descrição={}",
                pratoDTO.getNome(), pratoDTO.getPreco(), pratoDTO.getTempoDePreparo(), pratoDTO.getDescricao());

        try {
            Prato pratoExistente = pratoRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Tentativa de atualizar prato inexistente com ID: {}", id);
                        return new NotFoundException("Falha ao Identificar o id do prato");
                    }
            );

            String nomeAnterior = pratoExistente.getNome();
            BigDecimal precoAnterior = pratoExistente.getPreco();

            pratoExistente.setNome(pratoDTO.getNome());
            pratoExistente.setDescricao(pratoDTO.getDescricao());
            pratoExistente.setPreco(pratoDTO.getPreco());
            pratoExistente.setTempoDePreparo(pratoDTO.getTempoDePreparo());

            Prato pratoSalvo = pratoRepository.save(pratoExistente);

            log.debug("Prato atualizado: Nome '{}' -> '{}', Preço {} -> {}",
                    nomeAnterior, pratoSalvo.getNome(), precoAnterior, pratoSalvo.getPreco());
            log.info("Atualização do prato concluída com sucesso para ID: {}", id);

            PratoDTO pratoDTOAtualizado = ObjectMapper.parseObject(pratoSalvo, PratoDTO.class);
            return pratoDTOAtualizado;

        } catch (NotFoundException e) {
            log.error("Prato não encontrado para atualização com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao atualizar prato com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public void excluirPrato(Long id) {
        log.info("Iniciando exclusão do prato com ID: {}", id);

        try {
            Prato prato = pratoRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Tentativa de excluir prato inexistente com ID: {}", id);
                        return new NotFoundException("Id do prato não localizado ou inexistente");
                    }
            );

            String nomePrato = prato.getNome();
            BigDecimal preco = prato.getPreco();

            log.debug("Excluindo prato: Nome={}, Preço={}", nomePrato, preco);
            pratoRepository.deleteById(id);

            log.info("Prato excluído com sucesso: '{}' (ID: {}), Preço: {}", nomePrato, id, preco);

        } catch (NotFoundException e) {
            log.error("Prato não encontrado para exclusão com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao excluir prato com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}