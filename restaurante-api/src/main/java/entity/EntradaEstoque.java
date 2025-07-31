package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class EntradaEstoque {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Ingrediente ingrediente;

    private Double quantidade;
    private LocalDateTime dataEntrada;
    private String fornecedor;

    public EntradaEstoque() {
    }

    public EntradaEstoque(Ingrediente ingrediente, Double quantidade, LocalDateTime dataEntrada, String fornecedor) {
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.fornecedor = fornecedor;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EntradaEstoque that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(ingrediente, that.ingrediente) && Objects.equals(quantidade, that.quantidade) && Objects.equals(dataEntrada, that.dataEntrada) && Objects.equals(fornecedor, that.fornecedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingrediente, quantidade, dataEntrada, fornecedor);
    }
}
