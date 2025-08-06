package com.tomorrowproject.restaurante_api.services;

import com.tomorrowproject.restaurante_api.DTO.categoria.CategoriaDTO;
import com.tomorrowproject.restaurante_api.entity.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Validations {
    private static final Logger log = LoggerFactory.getLogger(Validations.class);

    public void validarIdNaoNulo(Long Id) {
        log.debug("validando Categoria Id não nulo");
        if (Id == null) {
            log.warn("o categoria Id    não pode ser nulo");
            throw new IllegalArgumentException(
                    "o categoria não pode ser nulo"
            );
        }
    }

    public void validarCategoriaNaoNulo(Categoria categoria) {
        log.debug("validando Categoria não nulo");
        if (categoria == null) {
            log.warn("tentativa de usar um Categoria nulo");
            throw new IllegalArgumentException(
                    "a CategoriaDTO não pode ser nula"
            );
        }
    }

    public void validarCategoriaDTONaoNulo(CategoriaDTO categoriaDTO) {
        log.debug("validando CategoriaDTO não nulo");
        if (categoriaDTO == null) {
            log.warn("tentativa de usar um CategoriaDTO nulo");
            throw new IllegalArgumentException(
                    "a CategoriaDTO não pode ser nula"
            );
        }
    }

    public void validarString(String string) {
        log.debug("validando string não nulo");
        if (string == null) {
            log.warn("tentativa de usar uma String invalida");
            throw new IllegalArgumentException(
                    "a String não pode ser nula"
            );
        }
    }
}
