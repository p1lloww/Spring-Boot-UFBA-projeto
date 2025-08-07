package com.tomorrowproject.restaurante_api.services;

import com.tomorrowproject.restaurante_api.DTO.pedido.PedidoDTO;
import com.tomorrowproject.restaurante_api.Mapper.ObjectMapper;
import com.tomorrowproject.restaurante_api.entity.Cliente;
import com.tomorrowproject.restaurante_api.entity.Pedido;
import com.tomorrowproject.restaurante_api.exception.NotFoundException;
import com.tomorrowproject.restaurante_api.repository.ClienteRepository;
import com.tomorrowproject.restaurante_api.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedidosDTO = ObjectMapper.parseListObjects(pedidos, PedidoDTO.class);

        return pedidosDTO;
    }

    @Transactional
    public PedidoDTO buscarPedidoPorID(Long Id) {

        Pedido pedido = pedidoRepository.findById(Id).orElseThrow(
                () -> new NotFoundException("Falha ao Identificar o id do pedido")
        );

        PedidoDTO pedidoDTO = ObjectMapper.parseObject(pedido, PedidoDTO.class);

        return pedidoDTO;
    }

    @Transactional
    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = ObjectMapper.parseObject(pedidoDTO, Pedido.class);

        Cliente cliente = clienteRepository.findById(pedidoDTO.getClientePedidoId()).orElseThrow(
                () -> new NotFoundException("Falha ao Identificar o id do cliente")
        );

        pedido.setCliente(cliente);
        pedido.setDataHora(LocalDateTime.now());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        PedidoDTO pedidoDTOSalvo = ObjectMapper.parseObject(pedidoSalvo, PedidoDTO.class);
        pedido.setClientePedidoId(pedido.getClientePedidoId());
        clienteRepository.save(cliente);

        return pedidoDTOSalvo;
    }

    @Transactional
    public PedidoDTO atualizarPedido(Long Id, PedidoDTO pedidoDTO) {
        log.info("id recebido do path variable: {}", Id);
        log.info("pedido existe: {}", pedidoRepository.existsById(Id));
        Pedido pedido = pedidoRepository.findById(Id).orElseThrow(
                () -> new NotFoundException("Falha ao Identificar o id do pedido")
        );

        Cliente cliente = clienteRepository.findById(pedidoDTO.getClientePedidoId()).orElseThrow(
                () -> new NotFoundException("Falha ao Identificar o id do cliente")
        );

        pedido.setCliente(cliente);
        pedido.setStatus(pedidoDTO.getStatus());
        pedido.setDataHora(LocalDateTime.now());
        pedido.setTotal(pedidoDTO.getTotal());

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);
        PedidoDTO pedidoDTOAtualizado = ObjectMapper.parseObject(pedidoAtualizado, PedidoDTO.class);

        return pedidoDTOAtualizado;
    }

    @Transactional
    public void excluirPedido(Long Id) {
        Pedido pedido = pedidoRepository.findById(Id).orElseThrow(
                () -> new NotFoundException("Id do pedido não localizado ou inexistente")
        );
        pedidoRepository.deleteById(Id);
    }
}
