package DTO;

import enums.StatusMesa;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Range;

public class MesaDTO {


    @NotNull(message = "o numero da mesa é invalido")
    @Min(message = "o numero da mesa precisa ser maior que zero", value = 1)
    @PositiveOrZero(message = "o numero da mesa é invalido")
    @Range
    private Integer numero;

    @NotNull(message = "a capacidade é invalida")
    @Positive(message = "a capacidade precisa ser positiva")
    private Integer capacidade;

    @NotNull(message = "o status não pode ser nulo")
    private StatusMesa status;

    public MesaDTO() {
    }

    public MesaDTO(Integer numero, Integer capacidade, StatusMesa status) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.status = status;
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

    public StatusMesa getStatus() {
        return status;
    }

    public void setStatus(StatusMesa status) {
        this.status = status;
    }
}
