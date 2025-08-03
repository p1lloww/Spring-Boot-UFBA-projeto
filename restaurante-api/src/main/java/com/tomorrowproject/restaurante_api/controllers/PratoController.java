package com.tomorrowproject.restaurante_api.controllers;

import com.tomorrowproject.restaurante_api.services.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/prato")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    //@GetMapping("/listarTodosOsPratos")
    //public ResponseEntity<List<>>
}
