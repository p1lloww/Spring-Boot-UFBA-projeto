package controllers;

import DTO.categoria.CategoriaDTO;
import entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {


    @GetMapping("/findAll")
    public ResponseEntity<CategoriaDTO> findAll() {
    }
}
