package com.tomorrowproject.restaurante_api.controllers;

import com.tomorrowproject.restaurante_api.DTO.prato.PratoDTO;
import com.tomorrowproject.restaurante_api.Mapper.ObjectMapper;
import com.tomorrowproject.restaurante_api.entity.Categoria;
import com.tomorrowproject.restaurante_api.entity.Prato;
import com.tomorrowproject.restaurante_api.services.PratoService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/prato")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    @GetMapping("/")
    public ResponseEntity<List<PratoDTO>> findAll() {
        List<PratoDTO> pratosDTO = pratoService.buscarTodosOsPratos();
        return ResponseEntity.ok(pratosDTO);
    }

    @PostMapping("/criarPrato")
    public ResponseEntity<PratoDTO> criarPrato(@RequestBody PratoDTO pratoDTO) {
        PratoDTO pratoDTOCriado = pratoService.criarPrato(pratoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pratoDTOCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PratoDTO> atualizarPrato(@PathParam("id") Long Id, PratoDTO pratoDTO) {
        PratoDTO pratoDTOCriado = pratoService.atualizarPrato(Id, pratoDTO);
        return ResponseEntity.ok(pratoDTOCriado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrato(@PathParam("id") Long Id) {
        pratoService.excluirPrato(Id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mock")
    public void mock() {
        for (int i = 0; i <= 3; i++) {
            PratoDTO pratoDTO = new PratoDTO();
            pratoDTO.setNome("test");
            pratoDTO.setPreco(BigDecimal.valueOf(i));
            pratoDTO.setTempoDePreparo(i);
            pratoDTO.setDescricao("teste");

            pratoService.criarPrato(pratoDTO);
        }
    }
}
