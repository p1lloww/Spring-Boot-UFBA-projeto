package backup;

import DTO.pedido.PedidoDTO;
import DTO.prato.PratoDTO;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class PratoPedidoDTO {


    @NotNull(message = "o pedido não pode ser nulo")
    private PedidoDTO pedido;

    @NotNull(message = "o prato não pode swer nulo")
    private PratoDTO prato;

    @NotNull(message = "a quantidade não pode ser nula")
    @Min(message = "a quantidade precisa ser no minimo 1", value = 1)
    @Positive(message = "a quantidade precisa ser positiva")
    private Integer quantidade;

    @NotNull(message = "a descrição não pode ser nula")
    @NotEmpty(message = "a descrição não pode estare vazia")
    @NotBlank(message = "a descrição não pode estare vazia")
    private String descricao;

    @NotNull(message = "o subtotal não pode ser nul")
    @Min(message = "o subtotal precisa ser no minimo 0", value = 0)
    @PositiveOrZero(message = "o subtotal precisa ser positivo ou zero")
    private BigDecimal subtotal;

    public PratoPedidoDTO() {
    }

    public PratoPedidoDTO(PedidoDTO pedido, PratoDTO prato, Integer quantidade, String descricao, BigDecimal subtotal) {
        this.pedido = pedido;
        this.prato = prato;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.subtotal = subtotal;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }

    public PratoDTO getPrato() {
        return prato;
    }

    public void setPrato(PratoDTO prato) {
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
}
