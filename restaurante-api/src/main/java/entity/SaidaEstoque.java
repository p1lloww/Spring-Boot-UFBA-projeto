package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class SaidaEstoque {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Ingrediente ingrediente;

    private Double quantidade;
    private LocalDateTime saidaEntrada;
    private String motivo;

    public SaidaEstoque() {
    }

    public SaidaEstoque(Ingrediente ingrediente, Double quantidade, LocalDateTime saidaEntrada, String motivo) {
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
        this.saidaEntrada = saidaEntrada;
        this.motivo = motivo;
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

    public LocalDateTime getSaidaEntrada() {
        return saidaEntrada;
    }

    public void setSaidaEntrada(LocalDateTime saidaEntrada) {
        this.saidaEntrada = saidaEntrada;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SaidaEstoque that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(ingrediente, that.ingrediente) && Objects.equals(quantidade, that.quantidade) && Objects.equals(saidaEntrada, that.saidaEntrada) && Objects.equals(motivo, that.motivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingrediente, quantidade, saidaEntrada, motivo);
    }
}
