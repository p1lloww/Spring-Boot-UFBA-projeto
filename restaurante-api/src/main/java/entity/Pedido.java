package entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import enums.StatusPedido;

@Entity
public class Pedido {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @OneToOne
    private Mesa mesa;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    private BigDecimal total;
    private LocalDateTime dataHora;
}
