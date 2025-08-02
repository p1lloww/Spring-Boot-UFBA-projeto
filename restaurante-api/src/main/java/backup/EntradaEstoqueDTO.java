package backup;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class EntradaEstoqueDTO {


    private IngredienteDTO ingrediente;

    @NotNull(message = "digite uma quantidade valida")
    @Min(message = "quantidade invalida", value = 0)
    @Positive(message = "a entrada precisa ser positiva")
    private Double quantidade;

    @NotNull(message = "adicione uma data valida")
    @PastOrPresent(message = "a data precisa ser no presente ou no passado")
    private LocalDateTime dataEntrada;

    @NotNull(message = "digite um fornecedor valido")
    @NotEmpty(message = "digite um fornecedor valido")
    @NotBlank(message = "digite um fornecedor valido")
    private String fornecedor;

    public EntradaEstoqueDTO() {
    }

    public EntradaEstoqueDTO(IngredienteDTO ingrediente, Double quantidade, LocalDateTime dataEntrada, String fornecedor) {
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
        this.dataEntrada = dataEntrada;
        this.fornecedor = fornecedor;
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

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
}
