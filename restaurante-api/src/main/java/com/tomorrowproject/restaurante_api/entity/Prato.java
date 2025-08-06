package com.tomorrowproject.restaurante_api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Prato {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer tempoDePreparo;

    public Prato() {
    }

    public Prato(String nome, String descricao, BigDecimal preco, Integer tempoDePreparo, Categoria categoria) {
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

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Prato prato)) return false;
        return Objects.equals(id, prato.id) && Objects.equals(nome, prato.nome) && Objects.equals(descricao, prato.descricao) && Objects.equals(preco, prato.preco) && Objects.equals(tempoDePreparo, prato.tempoDePreparo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, preco, tempoDePreparo);
    }
}
