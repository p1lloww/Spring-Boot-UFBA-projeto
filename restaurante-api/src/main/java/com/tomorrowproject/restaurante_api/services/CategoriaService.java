package com.tomorrowproject.restaurante_api.services;

import com.tomorrowproject.restaurante_api.DTO.categoria.CategoriaDTO;
import com.tomorrowproject.restaurante_api.DTO.prato.PratoDTO;
import com.tomorrowproject.restaurante_api.Mapper.ObjectMapper;
import com.tomorrowproject.restaurante_api.entity.Categoria;
import com.tomorrowproject.restaurante_api.entity.Prato;
import com.tomorrowproject.restaurante_api.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.sql.exec.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tomorrowproject.restaurante_api.repository.CategoriaRepository;
import com.tomorrowproject.restaurante_api.repository.PratoRepository;

import java.util.List;

@Service
public class CategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PratoRepository pratoRepository;

    @Transactional
    public List<CategoriaDTO> buscarTodasAsCategorias() {
        log.info("Iniciando busca de todas as categorias ordenadas por nome");

        try {
            List<Categoria> categorias = categoriaRepository.findAllByOrderByNomeAsc();
            log.debug("Encontradas {} categorias na base de dados", categorias.size());

            List<CategoriaDTO> categoriasDTO = ObjectMapper.parseListObjects(categorias, CategoriaDTO.class);

            if (categorias.isEmpty()) {
                log.warn("Nenhuma categoria encontrada na base de dados");
            } else {
                log.info("Busca de categorias concluída com sucesso. Total: {}", categoriasDTO.size());
            }

            return categoriasDTO;

        } catch (Exception e) {
            log.error("Erro ao buscar todas as categorias: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public CategoriaDTO buscarCategoriaPorID(Long id) {
        log.info("Iniciando busca da categoria com ID: {}", id);

        try {
            Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Categoria não encontrada com ID: {}", id);
                        return new NotFoundException("Id da categoria não localizado ou inexistente");
                    }
            );

            log.debug("Categoria encontrada: {} (ID: {})", categoria.getNome(), id);
            CategoriaDTO categoriaDTO = ObjectMapper.parseObject(categoria, CategoriaDTO.class);

            log.info("Busca da categoria concluída com sucesso para ID: {}", id);
            return categoriaDTO;

        } catch (NotFoundException e) {
            log.error("Categoria não encontrada com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao buscar categoria com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public CategoriaDTO criarCategoria(CategoriaDTO categoriaDTO) {
        log.info("Iniciando criação de nova categoria: {}", categoriaDTO.getNome());
        log.debug("Dados da categoria a ser criada: Nome={}, Descrição={}",
                categoriaDTO.getNome(), categoriaDTO.getDescricao());

        try {
            Categoria categoria = ObjectMapper.parseObject(categoriaDTO, Categoria.class);
            Categoria categoriaSalva = categoriaRepository.save(categoria);

            log.info("Categoria criada com sucesso. ID gerado: {}, Nome: {}",
                    categoriaSalva.getId(), categoriaSalva.getNome());

            return ObjectMapper.parseObject(categoriaSalva, CategoriaDTO.class);

        } catch (Exception e) {
            log.error("Erro ao criar categoria '{}': {}", categoriaDTO.getNome(), e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public CategoriaDTO adicionarPrato(Long pratoId, Long categoriaId) {
        log.info("Iniciando adição do prato ID {} à categoria ID {}", pratoId, categoriaId);

        try {
            Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(
                    () -> {
                        log.warn("Categoria não encontrada com ID: {}", categoriaId);
                        return new IllegalArgumentException("Id da categoria não localizado ou inexistente");
                    }
            );

            Prato prato = pratoRepository.findById(pratoId).orElseThrow(
                    () -> {
                        log.warn("Prato não encontrado com ID: {}", pratoId);
                        return new IllegalArgumentException("Id do prato não localizado ou inexistente");
                    }
            );

            log.debug("Associando prato '{}' à categoria '{}'", prato.getNome(), categoria.getNome());
            categoria.addPrato(prato);
            Categoria categoriaSalva = categoriaRepository.save(categoria);

            log.info("Prato '{}' adicionado com sucesso à categoria '{}' (Categoria ID: {})",
                    prato.getNome(), categoria.getNome(), categoriaId);

            CategoriaDTO categoriaDTOSalva = ObjectMapper.parseObject(categoriaSalva, CategoriaDTO.class);
            return categoriaDTOSalva;

        } catch (IllegalArgumentException e) {
            log.error("Erro de validação ao adicionar prato {} à categoria {}: {}", pratoId, categoriaId, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao adicionar prato {} à categoria {}: {}", pratoId, categoriaId, e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public CategoriaDTO atualizarCategoria(Long id, CategoriaDTO categoriaDTO) {
        log.info("Iniciando atualização da categoria com ID: {}", id);
        log.debug("Novos dados: Nome={}, Descrição={}",
                categoriaDTO.getNome(), categoriaDTO.getDescricao());

        try {
            Categoria categoriaExistente = categoriaRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Tentativa de atualizar categoria inexistente com ID: {}", id);
                        return new NotFoundException("Id da categoria não localizado ou inexistente");
                    }
            );

            String nomeAnterior = categoriaExistente.getNome();
            categoriaExistente.setNome(categoriaDTO.getNome());
            categoriaExistente.setDescricao(categoriaDTO.getDescricao());
            categoriaExistente.setPratos(ObjectMapper.parseListObjects(categoriaDTO.getPratos(), Prato.class));

            Categoria categoriaSalva = categoriaRepository.save(categoriaExistente);

            log.debug("Categoria atualizada: '{}' -> '{}'", nomeAnterior, categoriaSalva.getNome());
            log.info("Atualização da categoria concluída com sucesso para ID: {}", id);

            CategoriaDTO categoriaSalvaDTO = ObjectMapper.parseObject(categoriaSalva, CategoriaDTO.class);
            return categoriaSalvaDTO;

        } catch (NotFoundException e) {
            log.error("Categoria não encontrada para atualização com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao atualizar categoria com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public void excluirCategoria(Long id) {
        log.info("Iniciando exclusão da categoria com ID: {}", id);

        try {
            Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Tentativa de excluir categoria inexistente com ID: {}", id);
                        return new NotFoundException("Id da categoria não localizado ou inexistente");
                    }
            );

            String nomeCategoria = categoria.getNome();
            int quantidadePratos = categoria.getPratos() != null ? categoria.getPratos().size() : 0;

            log.debug("Excluindo categoria '{}' que possui {} prato(s)", nomeCategoria, quantidadePratos);
            categoriaRepository.deleteById(id);

            log.info("Categoria excluída com sucesso: '{}' (ID: {})", nomeCategoria, id);

        } catch (NotFoundException e) {
            log.error("Categoria não encontrada para exclusão com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao excluir categoria com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public List<PratoDTO> buscarPratosPorCategoriaId(Long id) {
        log.info("Iniciando busca de pratos da categoria ID: {}", id);

        try {
            Categoria categoriaEntity = categoriaRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Categoria não encontrada com ID: {}", id);
                        return new NotFoundException("Id da categoria não localizado ou inexistente");
                    }
            );

            List<Prato> pratosEntity = categoriaEntity.getPratos();
            int quantidadePratos = pratosEntity != null ? pratosEntity.size() : 0;

            log.debug("Categoria '{}' possui {} prato(s)", categoriaEntity.getNome(), quantidadePratos);

            List<PratoDTO> pratosDTO = ObjectMapper.parseListObjects(pratosEntity, PratoDTO.class);

            log.info("Busca de pratos concluída para categoria '{}' (ID: {}). Total de pratos: {}",
                    categoriaEntity.getNome(), id, quantidadePratos);

            return pratosDTO;

        } catch (NotFoundException e) {
            log.error("Categoria não encontrada para busca de pratos com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao buscar pratos da categoria ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}