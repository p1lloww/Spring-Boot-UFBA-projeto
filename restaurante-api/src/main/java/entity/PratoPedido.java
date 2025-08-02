package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class PratoPedido {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Prato prato;

    private Integer quantidade;
    private String descricao;
    private BigDecimal subtotal;

    public PratoPedido() {
    }

    public PratoPedido(Pedido pedido, Prato prato, Integer quantidade, String descricao, BigDecimal subtotal) {
        this.pedido = pedido;
        this.prato = prato;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.subtotal = subtotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PratoPedido that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(pedido, that.pedido) && Objects.equals(prato, that.prato) && Objects.equals(quantidade, that.quantidade) && Objects.equals(descricao, that.descricao) && Objects.equals(subtotal, that.subtotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedido, prato, quantidade, descricao, subtotal);
    }
}
