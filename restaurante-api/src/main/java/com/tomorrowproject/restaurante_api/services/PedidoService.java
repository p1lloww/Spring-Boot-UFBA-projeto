package com.tomorrowproject.restaurante_api.services;

import com.tomorrowproject.restaurante_api.DTO.pedido.CriarPedidoDTO;
import com.tomorrowproject.restaurante_api.DTO.pedido.PedidoDTO;
import com.tomorrowproject.restaurante_api.Mapper.ObjectMapper;
import com.tomorrowproject.restaurante_api.entity.Cliente;
import com.tomorrowproject.restaurante_api.entity.Pedido;
import com.tomorrowproject.restaurante_api.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public List<PedidoDTO> buscarTodosOsPedidos() {

        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedidosDTO = ObjectMapper.parseListObjects(pedidos, PedidoDTO.class);

        return pedidosDTO;
    }

    @Transactional
    public PedidoDTO buscarPedidoPorID(Long Id) {

        Pedido pedido = pedidoRepository.findById(Id).orElseThrow(
                () -> new IllegalArgumentException()
        );

        PedidoDTO pedidoDTO = ObjectMapper.parseObject(pedido, PedidoDTO.class);

        return pedidoDTO;
    }

    @Transactional
    public PedidoDTO criarPedido(CriarPedidoDTO pedidoDTO) {
        pedidoDTO.setDataHora(LocalDateTime.now());
        Pedido pedido = ObjectMapper.parseObject(pedidoDTO, Pedido.class);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        PedidoDTO pedidoDTOSalvo = ObjectMapper.parseObject(pedidoSalvo, PedidoDTO.class);

        return pedidoDTOSalvo;
    }

    @Transactional
    public List<PedidoDTO> criarPedidosDeTeste(int quantidade) {
        List<PedidoDTO> pedidosCriados = new ArrayList<>();

        for (int i = 1; i <= quantidade; i++) {
            CriarPedidoDTO pedidoTeste = new CriarPedidoDTO();
            pedidoTeste.setClienteId((long) i);
            pedidoTeste.setObservacoes("TesteObservacao" + i);
            pedidoTeste.setDataHora(LocalDateTime.now());

            // Se tiver outros campos, seguir o padrão:
            // pedidoTeste.setNome("TesteNome" + i);
            // pedidoTeste.setIdade("TesteIdade" + i);

            PedidoDTO pedidoCriado = criarPedido(pedidoTeste);
            pedidosCriados.add(pedidoCriado);
        }

        return pedidosCriados;
    }

    @Transactional
    public PedidoDTO atualizarPedido(Long Id, PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoRepository.findById(Id).orElseThrow(
                () -> new IllegalArgumentException()
        );
        pedido.setCliente(ObjectMapper.parseObject(pedidoDTO.getCliente(), Cliente.class));
        pedido.setStatus(pedidoDTO.getStatus());
        pedido.setDataHora(LocalDateTime.now());
        pedido.setTotal(pedidoDTO.getTotal());
        PedidoDTO pedidoDTOAtualizado = ObjectMapper.parseObject(pedido, PedidoDTO.class);

        return pedidoDTOAtualizado;
    }

    @Transactional
    public void excluirPedido(Long Id) {
        pedidoRepository.deleteById(Id);
    }
}
