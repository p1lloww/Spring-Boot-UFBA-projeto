package Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Prato {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer tempoDePreparo;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "prato")
    private List<PratoIngrediente> ingredientes = new ArrayList<>();

    @OneToMany(mappedBy = "prato")
    private List<PratoIngrediente> pratoIngredientes = new ArrayList<>();
}
