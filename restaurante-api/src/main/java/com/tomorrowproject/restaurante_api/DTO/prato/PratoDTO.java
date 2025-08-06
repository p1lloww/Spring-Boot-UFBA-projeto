package com.tomorrowproject.restaurante_api.DTO.prato;

import com.tomorrowproject.restaurante_api.DTO.categoria.CategoriaDTO;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class PratoDTO {

    @NotNull(message = "o nome não pode ser nulo")
    @NotEmpty(message = "o nome não pode estar vazio")
    @NotBlank(message = "o nome não pode estar vazio")
    private String nome;

    @NotNull(message = "a descrição não pode ser nula")
    @NotEmpty(message = "a descrição não pode estar vazia")
    @NotBlank(message = "a descrição não pode estar vazia")
    private String descricao;

    @NotNull(message = "o preço não pode ser nulo")
    @Min(message = "o preciso precisa ser maior que 0", value = 1)
    private BigDecimal preco;

    @NotNull(message = "o tempo de preparo não pode ser nulo")
    @Min(message = "o tempo de preparo precisa ser maior que 0", value = 1)
    private Integer tempoDePreparo;

    public PratoDTO() {
    }

    public PratoDTO(String nome, String descricao, BigDecimal preco, Integer tempoDePreparo, CategoriaDTO categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.tempoDePreparo = tempoDePreparo;
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getTempoDePreparo() {
        return tempoDePreparo;
    }

    public void setTempoDePreparo(Integer tempoDePreparo) {
        this.tempoDePreparo = tempoDePreparo;
    }
}
