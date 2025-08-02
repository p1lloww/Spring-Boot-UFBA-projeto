package DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PratoIngredienteDTO {


    @NotNull(message = "o prato não pode ser nulo")
    private PratoDTO prato;
    @NotNull(message = "o ingrediente não pode ser nulo")
    private IngredienteDTO ingrediente;
    @NotNull(message = "a quantidade não pode ser nula")
    @Min(message = "a quantidade precisa ser no minimo 1", value = 1)
    @Positive(message = "a quantidade precisa ser positiva")
    private Double quantidade;

    public PratoIngredienteDTO() {
    }

    public PratoIngredienteDTO(PratoDTO prato, IngredienteDTO ingrediente, Double quantidade) {
        this.prato = prato;
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
    }

    public PratoDTO getPrato() {
        return prato;
    }

    public void setPrato(PratoDTO prato) {
        this.prato = prato;
    }

    public IngredienteDTO getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(IngredienteDTO ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }
}
