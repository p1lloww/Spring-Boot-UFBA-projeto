package backup;

import entity.Prato;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class PratoIngrediente {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Prato prato;

    @ManyToOne
    private Ingrediente ingrediente;

    private Double quantidade;

    public PratoIngrediente() {
    }

    public PratoIngrediente(Prato prato, Ingrediente ingrediente, Double quantidade) {
        this.prato = prato;
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
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

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PratoIngrediente that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(prato, that.prato) && Objects.equals(ingrediente, that.ingrediente) && Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, prato, ingrediente, quantidade);
    }
}
