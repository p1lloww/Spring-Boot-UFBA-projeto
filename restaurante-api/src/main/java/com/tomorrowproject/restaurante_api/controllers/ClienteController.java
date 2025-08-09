package com.tomorrowproject.restaurante_api.controllers;

import com.tomorrowproject.restaurante_api.DTO.cliente.ClienteDTO;
import com.tomorrowproject.restaurante_api.services.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Clientes 🧑‍🤝‍🧑", description = "Endpoints para gerenciamento de clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista completa de todos os clientes cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> clienteDTOs = clienteService.buscarTodosOsClientes();
        return ResponseEntity.ok(clienteDTOs);
    }

    @Operation(summary = "Busca um cliente por ID", description = "Retorna um cliente específico baseado no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado e retornado")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable("id") Long id) {
        ClienteDTO clienteDTO = clienteService.buscarClientePorID(id);
        return ResponseEntity.ok(clienteDTO);
    }

    @Operation(summary = "Cria um novo cliente", description = "Cria e salva um novo cliente com base nos dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso",
            content = @Content(schema = @Schema(implementation = ClienteDTO.class)))
    @ApiResponse(responseCode = "400", description = "Dados de cliente inválidos")
    @PostMapping("/criarCliente")
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCriada = clienteService.criarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriada);
    }

    @Operation(summary = "Atualiza um cliente", description = "Atualiza os dados de um cliente existente")
    @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable("id") Long Id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizada = clienteService.atualizarCliente(Id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizada);
    }

    @Operation(summary = "Deleta um cliente", description = "Exclui um cliente do sistema")
    @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") Long Id) {
        clienteService.excluirCliente(Id);
        return ResponseEntity.noContent().build();
    }
}