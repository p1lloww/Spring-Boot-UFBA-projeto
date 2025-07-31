package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class ItemPedido {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Prato prato;

    private Integer quantidade;
    private String descricao;
    private BigDecimal subtotal;
}
