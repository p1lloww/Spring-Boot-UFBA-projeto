package com.tomorrowproject.restaurante_api.controllers;

import com.tomorrowproject.restaurante_api.DTO.prato.PratoDTO;
import com.tomorrowproject.restaurante_api.services.PratoService;

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

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/prato")
@Tag(name = "Pratos 🍕", description = "Endpoints para gerenciamento de pratos do cardápio")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    @Operation(summary = "Lista todos os pratos", description = "Retorna uma lista de todos os pratos cadastrados no cardápio")
    @ApiResponse(responseCode = "200", description = "Lista de pratos retornada com sucesso")
    @GetMapping()
    public ResponseEntity<List<PratoDTO>> findAll() {
        List<PratoDTO> pratosDTO = pratoService.buscarTodosOsPratos();
        return ResponseEntity.ok(pratosDTO);
    }

    @Operation(summary = "Busca um prato por ID", description = "Retorna um prato específico baseado no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Prato encontrado e retornado")
    @ApiResponse(responseCode = "404", description = "Prato não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<PratoDTO> buscarPorId(@PathVariable("id") Long id) {
        PratoDTO pratoDTO = pratoService.buscarPratoPorID(id);
        return ResponseEntity.ok(pratoDTO);
    }

    @Operation(summary = "Cria um novo prato", description = "Cria e salva um novo prato no cardápio")
    @ApiResponse(responseCode = "201", description = "Prato criado com sucesso",
            content = @Content(schema = @Schema(implementation = PratoDTO.class)))
    @ApiResponse(responseCode = "400", description = "Dados de prato inválidos")
    @PostMapping
    public ResponseEntity<PratoDTO> criarPrato(@Valid @RequestBody PratoDTO pratoDTO) {
        PratoDTO pratoDTOCriado = pratoService.criarPrato(pratoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pratoDTOCriado);
    }

    @Operation(summary = "Atualiza um prato", description = "Atualiza os dados de um prato existente no cardápio")
    @ApiResponse(responseCode = "200", description = "Prato atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Prato não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<PratoDTO> atualizarPrato(@PathVariable("id") Long Id, @Valid @RequestBody PratoDTO pratoDTO) {
        PratoDTO pratoDTOCriado = pratoService.atualizarPrato(Id, pratoDTO);
        return ResponseEntity.ok(pratoDTOCriado);
    }

    @Operation(summary = "Deleta um prato", description = "Exclui um prato do cardápio")
    @ApiResponse(responseCode = "204", description = "Prato deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Prato não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrato(@PathVariable("id") Long Id) {
        pratoService.excluirPrato(Id);
        return ResponseEntity.noContent().build();
    }
}
