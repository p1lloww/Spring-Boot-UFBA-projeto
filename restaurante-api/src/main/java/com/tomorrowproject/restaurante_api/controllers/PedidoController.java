package com.tomorrowproject.restaurante_api.controllers;

import com.tomorrowproject.restaurante_api.DTO.cliente.ClienteDTO;
import com.tomorrowproject.restaurante_api.DTO.pedido.CriarPedidoDTO;
import com.tomorrowproject.restaurante_api.DTO.pedido.PedidoDTO;
import com.tomorrowproject.restaurante_api.repository.PedidoRepository;
import com.tomorrowproject.restaurante_api.services.ClienteService;
import com.tomorrowproject.restaurante_api.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    public PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll() {

        List<PedidoDTO> pedidoDTOs = pedidoService.buscarTodosOsPedidos();
        return ResponseEntity.ok(pedidoDTOs);
    }

    @PostMapping("/criarPedido")
    public ResponseEntity<PedidoDTO> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoCriado = pedidoService.criarPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable("id") Long Id, @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoAtualizada = pedidoService.atualizarPedido(Id, pedidoDTO);
        return ResponseEntity.ok(pedidoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable("id") Long Id) {
        pedidoService.excluirPedido(Id);
        return ResponseEntity.noContent().build();
    }
}
