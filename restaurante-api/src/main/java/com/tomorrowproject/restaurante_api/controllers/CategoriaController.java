package com.tomorrowproject.restaurante_api.controllers;

import com.tomorrowproject.restaurante_api.DTO.categoria.CategoriaDTO;
import com.tomorrowproject.restaurante_api.DTO.prato.PratoDTO;
import com.tomorrowproject.restaurante_api.services.CategoriaService;

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
@RequestMapping("/categoria")
@Tag(name = "Categorias 🏷️", description = "Endpoints para gerenciamento de categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Operation(summary = "Lista todas as categorias 📜",
            description = "Retorna uma lista de todas as categorias cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de categorias retornada com sucesso")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> categoriaDTOs = categoriaService.buscarTodasAsCategorias();
        return ResponseEntity.ok(categoriaDTOs);
    }

    @Operation(summary = "Busca uma categoria por ID 🔎",
            description = "Retorna uma categoria específica baseada no ID fornecido")
    @ApiResponse(responseCode = "200", description = "Categoria encontrada e retornada")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<CategoriaDTO> buscarPorId(@PathVariable("id") Long id) {
        CategoriaDTO categoriaDTO = categoriaService.BuscarCategoriaPorID(id);
        return ResponseEntity.ok(categoriaDTO);
    }

    @Operation(summary = "Cria uma nova categoria ✨",
            description = "Cria e salva uma nova categoria com base nos dados fornecidos")
    @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso",
            content = @Content(schema = @Schema(implementation = CategoriaDTO.class)))
    @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    @PostMapping("/criarCategoria")
    public ResponseEntity<CategoriaDTO> criarCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaCriada = categoriaService.criarCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriada);
    }

    @Operation(summary = "Adiciona um prato a uma categoria 🍲➡️🏷️",
            description = "Associa um prato existente a uma categoria existente")
    @ApiResponse(responseCode = "200", description = "Prato adicionado à categoria com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria ou Prato não encontrados")
    @PostMapping("/adicionarPrato/{idCategoria}/{idPrato}")
    public ResponseEntity<CategoriaDTO> adicionarPrato(
            @PathVariable("idCategoria") Long idCategoria, @PathVariable("idPrato") Long idPrato
    ) {
        CategoriaDTO categoriaDTOSalva = categoriaService.adicionarPrato(idPrato, idCategoria);
        return ResponseEntity.ok(categoriaDTOSalva);
    }

    @Operation(summary = "Atualiza uma categoria 🔄",
            description = "Atualiza os dados de uma categoria existente")
    @ApiResponse(responseCode = "200", description = "Categoria atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable("id") Long Id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaAtualizada = categoriaService.atualizarCategoria(Id, categoriaDTO);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @Operation(summary = "Deleta uma categoria 🗑️",
            description = "Exclui uma categoria do sistema")
    @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable("id") Long Id) {
        categoriaService.excluirCategoria(Id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Lista os pratos de uma categoria 🍽️",
            description = "Retorna uma lista de todos os pratos de uma categoria específica")
    @ApiResponse(responseCode = "200", description = "Lista de pratos retornada com sucesso")
    @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    @GetMapping("/{id}/pratos")
    public ResponseEntity<List<PratoDTO>> listarPratosDeUmaCategoria(@PathVariable("id") Long Id) {
        List<PratoDTO> pratos = categoriaService.buscarPratosPorCategoriaId(Id);
        return ResponseEntity.ok(pratos);
    }
}