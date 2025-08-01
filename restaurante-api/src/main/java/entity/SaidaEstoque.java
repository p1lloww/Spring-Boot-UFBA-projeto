package entity;

import enums.MotivoEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class SaidaEstoque {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Ingrediente ingrediente;

    @OneToOne
    private Pedido pedido;

    private Double quantidade;
    private LocalDateTime dataSaida;
    private MotivoEnum motivo;

    public SaidaEstoque() {
    }

    public SaidaEstoque(Ingrediente ingrediente, Double quantidade, LocalDateTime saidaEntrada, MotivoEnum motivo) {
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
        this.dataSaida = saidaEntrada;
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
        return dataSaida;
    }

    public void setSaidaEntrada(LocalDateTime saidaEntrada) {
        dataSaida = saidaEntrada;
    }

    public MotivoEnum getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoEnum motivo) {
        this.motivo = motivo;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SaidaEstoque that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(ingrediente, that.ingrediente) && Objects.equals(quantidade, that.quantidade) && Objects.equals(dataSaida, that.dataSaida) && Objects.equals(motivo, that.motivo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingrediente, quantidade, dataSaida, motivo);
    }
}
