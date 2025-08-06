package com.tomorrowproject.restaurante_api.DTO.categoria;

import com.tomorrowproject.restaurante_api.DTO.prato.PratoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CategoriaDTO {


    @NotNull(message = "o nome é invalido")
    @NotEmpty(message = "o nome é invalido")
    @NotBlank(message = "o nome é invalido")
    private String nome;

    @NotNull(message = "a descrição é invalida")
    @NotEmpty(message = "a descrição é invalida")
    @NotBlank(message = "a descrição é invalida")
    private String descricao;

    @NotNull(message = "pratos é invalido")
    private List<PratoDTO> pratos;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PratoDTO> getPratos() {
        return pratos;
    }

    public void setPratos(List<PratoDTO> pratos) {
        this.pratos = pratos;
    }

    public void addPratoDTO(PratoDTO pratoIngredienteVar) {
        pratos.add(pratoIngredienteVar);
    }

    public void removePratoDTO(PratoDTO pratoIngredienteVar) {
        pratos.remove(pratoIngredienteVar);
    }
}
