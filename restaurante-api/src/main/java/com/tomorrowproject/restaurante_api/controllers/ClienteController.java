package com.tomorrowproject.restaurante_api.controllers;

import com.tomorrowproject.restaurante_api.DTO.cliente.ClienteDTO;
import com.tomorrowproject.restaurante_api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> clienteDTOs = clienteService.buscarTodosOsClientes();
        return ResponseEntity.ok(clienteDTOs);
    }

    @PostMapping("/criarCliente")
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCriada = clienteService.criarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable("id") Long Id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizada = clienteService.atualizarCliente(Id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") Long Id) {
        clienteService.excluirCliente(Id);
        return ResponseEntity.noContent().build();
    }

}
