package com.tomorrowproject.restaurante_api.services;

import com.tomorrowproject.restaurante_api.DTO.cliente.ClienteDTO;
import com.tomorrowproject.restaurante_api.DTO.prato.PratoDTO;
import com.tomorrowproject.restaurante_api.Mapper.ObjectMapper;
import com.tomorrowproject.restaurante_api.entity.Categoria;
import com.tomorrowproject.restaurante_api.entity.Cliente;
import com.tomorrowproject.restaurante_api.exception.NotFoundException;
import com.tomorrowproject.restaurante_api.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public List<ClienteDTO> buscarTodosOsClientes() {
        log.info("Iniciando busca de todos os clientes ordenados por nome");

        try {
            List<Cliente> clientes = clienteRepository.findAllByOrderByNomeAsc();
            log.debug("Encontrados {} clientes na base de dados", clientes.size());

            List<ClienteDTO> clientesDTO = ObjectMapper.parseListObjects(clientes, ClienteDTO.class);

            log.info("Busca de clientes concluída com sucesso. Total: {}", clientesDTO.size());
            return clientesDTO;

        } catch (Exception e) {
            log.error("Erro ao buscar todos os clientes: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public ClienteDTO buscarClientePorID(Long id) {
        log.info("Iniciando busca do cliente com ID: {}", id);

        try {
            Cliente cliente = clienteRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Cliente não encontrado com ID: {}", id);
                        return new NotFoundException("Id do cliente inexistente");
                    }
            );

            log.debug("Cliente encontrado: {} (ID: {})", cliente.getNome(), id);
            ClienteDTO clienteDTO = ObjectMapper.parseObject(cliente, ClienteDTO.class);

            log.info("Busca do cliente concluída com sucesso para ID: {}", id);
            return clienteDTO;

        } catch (NotFoundException e) {
            log.error("Cliente não encontrado com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao buscar cliente com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        log.info("Iniciando criação de novo cliente: {}", clienteDTO.getNome());
        log.debug("Dados do cliente a ser criado: Nome={}, Telefone={}, Endereço={}",
                clienteDTO.getNome(), clienteDTO.getTelefone(), clienteDTO.getEndereco());

        try {
            Cliente cliente = ObjectMapper.parseObject(clienteDTO, Cliente.class);
            Cliente clienteSalvo = clienteRepository.save(cliente);

            log.info("Cliente criado com sucesso. ID gerado: {}, Nome: {}",
                    clienteSalvo.getId(), clienteSalvo.getNome());

            ClienteDTO clienteDTOSalvo = ObjectMapper.parseObject(clienteSalvo, ClienteDTO.class);
            return clienteDTOSalvo;

        } catch (Exception e) {
            log.error("Erro ao criar cliente '{}': {}", clienteDTO.getNome(), e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public ClienteDTO atualizarCliente(Long id, ClienteDTO clienteDTO) {
        log.info("Iniciando atualização do cliente com ID: {}", id);
        log.debug("Novos dados: Nome={}, Telefone={}, Endereço={}",
                clienteDTO.getNome(), clienteDTO.getTelefone(), clienteDTO.getEndereco());

        try {
            Cliente cliente = clienteRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Tentativa de atualizar cliente inexistente com ID: {}", id);
                        return new NotFoundException("Id do cliente inexistente");
                    }
            );

            String nomeAnterior = cliente.getNome();
            cliente.setNome(clienteDTO.getNome());
            cliente.setEndereco(clienteDTO.getEndereco());
            cliente.setTelefone(clienteDTO.getTelefone());

            log.debug("Cliente atualizado: '{}' -> '{}'", nomeAnterior, cliente.getNome());
            ClienteDTO clienteDTOAtualizado = ObjectMapper.parseObject(cliente, ClienteDTO.class);

            log.info("Atualização do cliente concluída com sucesso para ID: {}", id);
            return clienteDTOAtualizado;

        } catch (NotFoundException e) {
            log.error("Cliente não encontrado para atualização com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao atualizar cliente com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public void excluirCliente(Long id) {
        log.info("Iniciando exclusão do cliente com ID: {}", id);

        try {
            Cliente cliente = clienteRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Tentativa de excluir cliente inexistente com ID: {}", id);
                        return new NotFoundException("Id do cliente não localizado ou inexistente");
                    }
            );

            String nomeCliente = cliente.getNome();
            clienteRepository.deleteById(id);

            log.info("Cliente excluído com sucesso: '{}' (ID: {})", nomeCliente, id);

        } catch (NotFoundException e) {
            log.error("Cliente não encontrado para exclusão com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao excluir cliente com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}