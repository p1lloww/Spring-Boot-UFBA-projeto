package com.tomorrowproject.restaurante_api.controllers;

import com.tomorrowproject.restaurante_api.DTO.categoria.CategoriaDTO;
import com.tomorrowproject.restaurante_api.DTO.prato.PratoDTO;
import com.tomorrowproject.restaurante_api.Mapper.ObjectMapper;
import com.tomorrowproject.restaurante_api.entity.Categoria;
import com.tomorrowproject.restaurante_api.entity.Prato;
import com.tomorrowproject.restaurante_api.repository.PratoRepository;
import com.tomorrowproject.restaurante_api.services.PratoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tomorrowproject.restaurante_api.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private PratoService pratoService;

    @Autowired
    private PratoRepository pratoRepository;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {

        List<CategoriaDTO> categoriaDTOs = categoriaService.buscarTodasAsCategorias();
        return ResponseEntity.ok(categoriaDTOs);
    }

    @PostMapping("/criarCategoria")
    public ResponseEntity<CategoriaDTO> criarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaCriada = categoriaService.criarCategoria(categoriaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaCriada);
    }

    @PostMapping("/adicionarPrato/{idCategoria}/{idPrato}")
    public ResponseEntity<CategoriaDTO> adicionarPrato(
            @PathVariable("idCategoria") Long idCategoria, @PathVariable("idPrato") Long idPrato
    ) {
        CategoriaDTO categoriaDTOSalva = categoriaService.adicionarPrato(idPrato, idCategoria);
        return ResponseEntity.ok(categoriaDTOSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> atualizarCategoria(@PathVariable("id") Long Id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO categoriaAtualizada = categoriaService.atualizarCategoria(Id, categoriaDTO);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable("id") Long Id) {
        categoriaService.excluirCategoria(Id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pratos")
    public ResponseEntity<List<PratoDTO>> listarPRatosDeUmaCategoria(@PathParam("id") Long Id) {
        List<PratoDTO> pratos = categoriaService.buscarPratosPorCategoriaId(Id);
        return ResponseEntity.ok(pratos);
    }

    @GetMapping("/mock")
    public ResponseEntity<CategoriaDTO> mock() {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setNome("teste");
        categoriaDTO.setDescricao("teste");

        CategoriaDTO categoriaSalva = categoriaService.criarCategoria(categoriaDTO);

        return ResponseEntity.ok(categoriaSalva);
    }
}
