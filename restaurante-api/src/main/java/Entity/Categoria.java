package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    List<Prato> pratos = new ArrayList<>();
}
