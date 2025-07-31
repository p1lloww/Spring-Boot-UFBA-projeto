package entity;

import jakarta.persistence.*;

@Entity
public class PratoIngrediente {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Prato prato;

    @ManyToOne
    private Ingrediente ingrediente;

    private Double quantidade;
}
