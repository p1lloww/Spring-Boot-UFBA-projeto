package DTO.pedido;

import DTO.cliente.ClienteDTO;
import backup.MesaDTO;
import backup.SaidaEstoqueDTO;
import enums.StatusPedido;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CriarPedidoDTO {

    private ClienteDTO cliente;

    private SaidaEstoqueDTO saidaEstoque;

    @NotNull(message = "o status não pode ser nulo")
    private StatusPedido status;

    @NotNull(message = "o total não pode ser nulo")
    @Min(message = "o minimo precisa ser maior que 0", value = 0)
    private BigDecimal total;

    @NotNull(message = "a data não pode ser nula")
    private LocalDateTime dataHora;

    public CriarPedidoDTO() {
    }

    public CriarPedidoDTO(ClienteDTO cliente, MesaDTO mesa, SaidaEstoqueDTO saidaEstoque, StatusPedido status, BigDecimal total, LocalDateTime dataHora) {
        this.cliente = cliente;
        this.saidaEstoque = saidaEstoque;
        this.status = status;
        this.total = total;
        this.dataHora = dataHora;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public SaidaEstoqueDTO getSaidaEstoque() {
        return saidaEstoque;
    }

    public void setSaidaEstoque(SaidaEstoqueDTO saidaEstoque) {
        this.saidaEstoque = saidaEstoque;
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
}
