package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Mesa {
    @Id
    @GeneratedValue
    private Long id;
    private Integer numero;
    private Integer capacidade;
    private String Status;

    public Mesa() {
    }

    public Mesa(Integer numero, Integer capacidade, String status) {
        this.numero = numero;
        this.capacidade = capacidade;
        Status = status;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Mesa mesa)) return false;
        return Objects.equals(id, mesa.id) && Objects.equals(numero, mesa.numero) && Objects.equals(capacidade, mesa.capacidade) && Objects.equals(Status, mesa.Status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, capacidade, Status);
    }
}
