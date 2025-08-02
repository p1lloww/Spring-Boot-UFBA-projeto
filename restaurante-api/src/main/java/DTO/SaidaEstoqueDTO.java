package DTO;

import enums.MotivoEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class SaidaEstoqueDTO {


    @NotNull(message = "o ingrediente não pode ser nulo")
    private IngredienteDTO ingrediente;

    @NotNull(message = "o pedido não pode ser nulo")
    private PedidoDTO pedido;

    @NotNull(message = "a quantidade não pode ser nula")
    @Min(message = "a qunatidade precisa ser no minio 1", value = 1)
    @Positive(message = "a quantidade precisa ser positiva")
    private Double quantidade;

    @NotNull(message = "a data não pode ser nula")
    private LocalDateTime dataSaida;

    @NotNull(message = "o motivo não pode ser nulo")
    private MotivoEnum motivo;

    public SaidaEstoqueDTO() {
    }

    public SaidaEstoqueDTO(IngredienteDTO ingrediente, PedidoDTO pedido, Double quantidade, LocalDateTime dataSaida, MotivoEnum motivo) {
        this.ingrediente = ingrediente;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.dataSaida = dataSaida;
        this.motivo = motivo;
    }

    public IngredienteDTO getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(IngredienteDTO ingrediente) {
        this.ingrediente = ingrediente;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public MotivoEnum getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoEnum motivo) {
        this.motivo = motivo;
    }
}
