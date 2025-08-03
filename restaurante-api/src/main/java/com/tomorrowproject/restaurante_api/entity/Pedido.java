package com.tomorrowproject.restaurante_api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.tomorrowproject.restaurante_api.enums.StatusPedido;

@Entity
public class Pedido {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private BigDecimal total;
    private LocalDateTime dataHora;

    public Pedido() {
    }

    public Pedido(Cliente cliente, StatusPedido status, BigDecimal total, LocalDateTime dataHora) {
        this.cliente = cliente;
        this.status = status;
        this.total = total;
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pedido pedido)) return false;
        return Objects.equals(id, pedido.id) && Objects.equals(cliente, pedido.cliente) && status == pedido.status && Objects.equals(total, pedido.total) && Objects.equals(dataHora, pedido.dataHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, status, total, dataHora);
    }
}
