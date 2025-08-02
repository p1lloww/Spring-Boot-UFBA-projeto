package backup;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Ingrediente {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String unidade;
    private Double estoqueAtual;
    private Double estoqueMinimo;

    @OneToMany(mappedBy = "ingrediente")
    private List<PratoIngrediente> pratoIngredientes = new ArrayList<>();

    public Ingrediente() {
    }

    public Ingrediente(String nome, String unidade, Double estoqueAtual, Double estoqueMinimo) {
        this.nome = nome;
        this.unidade = unidade;
        this.estoqueAtual = estoqueAtual;
        this.estoqueMinimo = estoqueMinimo;
    }

    public void addPratoIngrediente(PratoIngrediente pratoIngredienteVar) {
        pratoIngredientes.add(pratoIngredienteVar);
        pratoIngredienteVar.setIngrediente(this);
    }

    public void removePratoIngrediente(PratoIngrediente pratoIngredienteVar) {
        pratoIngredientes.remove(pratoIngredienteVar);
        pratoIngredienteVar.setIngrediente(null);
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

    public Long getId() {
        return id;
    }

    public List<PratoIngrediente> getPratoIngredientes() {
        return pratoIngredientes;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ingrediente that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(unidade, that.unidade) && Objects.equals(estoqueAtual, that.estoqueAtual) && Objects.equals(estoqueMinimo, that.estoqueMinimo) && Objects.equals(pratoIngredientes, that.pratoIngredientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, unidade, estoqueAtual, estoqueMinimo, pratoIngredientes);
    }
}
