package DTO;

import entity.PratoIngrediente;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class IngredienteDTO {


    @NotNull(message = "o nome é invalido")
    @NotEmpty(message = "o nome é invalido")
    @NotBlank(message = "o nome é invalido")
    private String nome;

    @NotNull(message = "a unidade é invalida")
    @NotEmpty(message = "a unidade é invalida")
    @NotBlank(message = "a unidade é invalida")
    private String unidade;

    @NotNull(message = "estoque atual invalido")
    @Min(message = "o estoque precisa ser maior que 0", value = 0)
    private Double estoqueAtual;

    @NotNull(message = "o estoque minimo é invalido")
    @Min(message = "o estoque minimo precisa ser maior que zero", value = 0)
    private Double estoqueMinimo;

    @NotNull(message = "o prato ingrediente é invalido")
    private List<PratoIngredienteDTO> pratoIngredientes;

    public IngredienteDTO() {
    }

    public IngredienteDTO(String nome, String unidade, Double estoqueAtual, Double estoqueMinimo) {
        this.nome = nome;
        this.unidade = unidade;
        this.estoqueAtual = estoqueAtual;
        this.estoqueMinimo = estoqueMinimo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(Double estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public Double getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(Double estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public List<PratoIngredienteDTO> getPratoIngredientes() {
        return pratoIngredientes;
    }

    public void addPratoIngrediente(PratoIngredienteDTO pratoIngredienteVar) {
        pratoIngredientes.add(pratoIngredienteVar);
    }

    public void removePratoIngrediente(PratoIngredienteDTO pratoIngredienteVar) {
        pratoIngredientes.remove(pratoIngredienteVar);
    }


}
