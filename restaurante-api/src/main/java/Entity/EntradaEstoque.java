package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class EntradaEstoque {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Ingrediente ingrediente;

    private Double quantidade;
    private LocalDateTime dataEntrada;
    private String fornecedor;
}
