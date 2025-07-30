package Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    private List<PratoIngrediente> pratos = new ArrayList<>();

    @OneToMany(mappedBy = "ingrediente")
    private List<PratoIngrediente> pratoIngredientes = new ArrayList<>();
}
