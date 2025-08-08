package com.tomorrowproject.restaurante_api.DTO.pedido;

import com.tomorrowproject.restaurante_api.DTO.cliente.ClienteDTO;
import com.tomorrowproject.restaurante_api.enums.StatusPedido;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;

    private ClienteDTO cliente;

    @NotNull(message = "o status não pode ser nulo")
    private StatusPedido status;

    @NotNull(message = "o total não pode ser nulo")
    @Min(message = "o minimo precisa ser maior que 0", value = 0)
    private BigDecimal total;

    private LocalDateTime dataHora;

    public PedidoDTO() {
    }

    public PedidoDTO(Long clientePedidoId, StatusPedido status, BigDecimal total, LocalDateTime dataHora) {
        this.pedidoId = clientePedidoId;
        this.status = status;
        this.total = total;
        this.dataHora = dataHora;
    }

    public Long getClientePedidoId() {
        return pedidoId;
    }

    public void setClientePedidoId(Long clientePedidoId) {
        this.pedidoId = clientePedidoId;
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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}
