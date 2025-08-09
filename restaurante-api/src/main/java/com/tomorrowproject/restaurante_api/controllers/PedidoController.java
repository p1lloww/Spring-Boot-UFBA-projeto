package com.tomorrowproject.restaurante_api.controllers;

import com.tomorrowproject.restaurante_api.DTO.pedido.PedidoDTO;
import com.tomorrowproject.restaurante_api.repository.PedidoRepository;
import com.tomorrowproject.restaurante_api.services.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
@Tag(name = "Pedidos 📝", description = "Endpoints para gerenciamento de pedidos")
public class PedidoController {

    @Autowired
    public PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService pedidoService;

    @Operation(summary = "Lista todos os pedidos", description = "Retorna uma lista completa de todos os pedidos feitos")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll() {
        List<PedidoDTO> pedidoDTOs = pedidoService.buscarTodosOsPedidos();
        return ResponseEntity.ok(pedidoDTOs);
    }

    @Operation(summary = "Busca um pedido por ID", description = "Retorna um pedido específico baseado no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Pedido encontrado e retornado")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable("id") Long id) {
        PedidoDTO pedidoDTO = pedidoService.buscarPedidoPorID(id);
        return ResponseEntity.ok(pedidoDTO);
    }

    @Operation(summary = "Cria um novo pedido", description = "Cria e salva um novo pedido com base nos dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso",
            content = @Content(schema = @Schema(implementation = PedidoDTO.class)))
    @ApiResponse(responseCode = "400", description = "Dados de pedido inválidos")
    @PostMapping
    public ResponseEntity<PedidoDTO> criarPedido(@Valid @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoCriado = pedidoService.criarPedido(pedidoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }

    @Operation(summary = "Atualiza um pedido", description = "Atualiza os dados de um pedido existente")
    @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizarPedido(@PathVariable("id") Long Id, @Valid @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoAtualizada = pedidoService.atualizarPedido(Id, pedidoDTO);
        return ResponseEntity.ok(pedidoAtualizada);
    }

    @Operation(summary = "Deleta um pedido", description = "Exclui um pedido do sistema")
    @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable("id") Long Id) {
        pedidoService.excluirPedido(Id);
        return ResponseEntity.noContent().build();
    }
}
