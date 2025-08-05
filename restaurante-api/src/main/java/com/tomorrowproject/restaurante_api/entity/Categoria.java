package com.tomorrowproject.restaurante_api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descricao;

    //@OneToMany(mappedBy = "categoria")
    @OneToMany
    List<Prato> pratos = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void addPrato(Prato prato){
        pratos.add(prato);
    }

    public void removePrato(Prato prato){
        pratos.remove(prato);
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

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Categoria categoria)) return false;
        return Objects.equals(id, categoria.id) && Objects.equals(nome, categoria.nome) && Objects.equals(descricao, categoria.descricao) && Objects.equals(pratos, categoria.pratos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, pratos);
    }
}
