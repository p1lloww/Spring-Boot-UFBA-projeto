package com.tomorrowproject.restaurante_api.services;

import com.tomorrowproject.restaurante_api.DTO.pedido.PedidoDTO;
import com.tomorrowproject.restaurante_api.Mapper.ObjectMapper;
import com.tomorrowproject.restaurante_api.entity.Cliente;
import com.tomorrowproject.restaurante_api.entity.Pedido;
import com.tomorrowproject.restaurante_api.enums.StatusPedido;
import com.tomorrowproject.restaurante_api.exception.NotFoundException;
import com.tomorrowproject.restaurante_api.repository.ClienteRepository;
import com.tomorrowproject.restaurante_api.repository.PedidoRepository;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public List<PedidoDTO> buscarTodosOsPedidos() {
        log.info("Iniciando busca de todos os pedidos");

        try {
            List<Pedido> pedidos = pedidoRepository.findAll();
            log.debug("Encontrados {} pedidos na base de dados", pedidos.size());

            List<PedidoDTO> pedidosDTO = ObjectMapper.parseListObjects(pedidos, PedidoDTO.class);

            if (pedidos.isEmpty()) {
                log.warn("Nenhum pedido encontrado na base de dados");
            } else {
                log.info("Busca de pedidos concluída com sucesso. Total: {}", pedidosDTO.size());
            }

            return pedidosDTO;

        } catch (Exception e) {
            log.error("Erro ao buscar todos os pedidos: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public PedidoDTO buscarPedidoPorID(Long id) {
        log.info("Iniciando busca do pedido com ID: {}", id);

        try {
            Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Pedido não encontrado com ID: {}", id);
                        return new NotFoundException("Falha ao Identificar o id do pedido");
                    }
            );

            log.debug("Pedido encontrado: ID={}, Cliente ID={}, Status={}, Total={}",
                    id, pedido.getCliente().getId(), pedido.getStatus(), pedido.getTotal());

            PedidoDTO pedidoDTO = ObjectMapper.parseObject(pedido, PedidoDTO.class);

            log.info("Busca do pedido concluída com sucesso para ID: {}", id);
            return pedidoDTO;

        } catch (NotFoundException e) {
            log.error("Pedido não encontrado com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao buscar pedido com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) {
        log.info("Iniciando criação de novo pedido para cliente ID: {}", pedidoDTO.getClientePedidoId());
        log.debug("Dados do pedido: Status={}, Total={}", pedidoDTO.getStatus(), pedidoDTO.getTotal());

        try {
            Pedido pedido = ObjectMapper.parseObject(pedidoDTO, Pedido.class);

            Cliente cliente = clienteRepository.findById(pedidoDTO.getClientePedidoId()).orElseThrow(
                    () -> {
                        log.warn("Cliente não encontrado com ID: {}", pedidoDTO.getClientePedidoId());
                        return new NotFoundException("Falha ao Identificar o id do cliente");
                    }
            );

            log.debug("Cliente encontrado: {} (ID: {})", cliente.getNome(), cliente.getId());

            pedido.setCliente(cliente);
            pedido.setDataHora(LocalDateTime.now());

            Pedido pedidoSalvo = pedidoRepository.save(pedido);

            log.info("Pedido criado com sucesso. ID gerado: {}, Cliente: {}, Total: {}",
                    pedidoSalvo.getId(), cliente.getNome(), pedidoSalvo.getTotal());

            PedidoDTO pedidoDTOSalvo = ObjectMapper.parseObject(pedidoSalvo, PedidoDTO.class);
            pedidoDTOSalvo.setClientePedidoId(cliente.getId());

            return pedidoDTOSalvo;

        } catch (NotFoundException e) {
            log.error("Erro ao criar pedido - Cliente não encontrado: {}", pedidoDTO.getClientePedidoId());
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao criar pedido para cliente {}: {}", pedidoDTO.getClientePedidoId(), e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public PedidoDTO atualizarPedido(Long id, PedidoDTO pedidoDTO) {
        log.info("Iniciando atualização do pedido com ID: {}", id);
        log.debug("Verificando existência do pedido ID: {} - Existe: {}", id, pedidoRepository.existsById(id));
        log.debug("Novos dados: Cliente ID={}, Status={}, Total={}",
                pedidoDTO.getClientePedidoId(), pedidoDTO.getStatus(), pedidoDTO.getTotal());

        try {
            Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Tentativa de atualizar pedido inexistente com ID: {}", id);
                        return new NotFoundException("Falha ao Identificar o id do pedido");
                    }
            );

            Cliente cliente = clienteRepository.findById(pedidoDTO.getClientePedidoId()).orElseThrow(
                    () -> {
                        log.warn("Cliente não encontrado com ID: {} para atualização do pedido {}",
                                pedidoDTO.getClientePedidoId(), id);
                        return new NotFoundException("Falha ao Identificar o id do cliente");
                    }
            );

            log.debug("Cliente encontrado: {} (ID: {})", cliente.getNome(), cliente.getId());

            StatusPedido statusAnterior = pedido.getStatus();
            BigDecimal totalAnterior = pedido.getTotal();

            pedido.setCliente(cliente);
            pedido.setStatus(pedidoDTO.getStatus());
            pedido.setDataHora(LocalDateTime.now());
            pedido.setTotal(pedidoDTO.getTotal());

            Pedido pedidoAtualizado = pedidoRepository.save(pedido);

            log.debug("Pedido atualizado: Status '{}' -> '{}', Total {} -> {}",
                    statusAnterior, pedido.getStatus(), totalAnterior, pedido.getTotal());
            log.info("Atualização do pedido concluída com sucesso para ID: {}", id);

            PedidoDTO pedidoDTOAtualizado = ObjectMapper.parseObject(pedidoAtualizado, PedidoDTO.class);
            return pedidoDTOAtualizado;

        } catch (NotFoundException e) {
            log.error("Erro de validação ao atualizar pedido ID {}: {}", id, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao atualizar pedido ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @Transactional
    public void excluirPedido(Long id) {
        log.info("Iniciando exclusão do pedido com ID: {}", id);

        try {
            Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                    () -> {
                        log.warn("Tentativa de excluir pedido inexistente com ID: {}", id);
                        return new NotFoundException("Id do pedido não localizado ou inexistente");
                    }
            );

            String clienteNome = pedido.getCliente() != null ? pedido.getCliente().getNome() : "N/A";
            StatusPedido status = pedido.getStatus();
            BigDecimal total = pedido.getTotal();

            log.debug("Excluindo pedido: Cliente={}, Status={}, Total={}", clienteNome, status, total);
            pedidoRepository.deleteById(id);

            log.info("Pedido excluído com sucesso: ID={}, Cliente={}, Total={}", id, clienteNome, total);

        } catch (NotFoundException e) {
            log.error("Pedido não encontrado para exclusão com ID: {}", id);
            throw e;
        } catch (Exception e) {
            log.error("Erro inesperado ao excluir pedido com ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}