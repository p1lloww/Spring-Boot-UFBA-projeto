package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Cliente {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String telefone;

    public Cliente() {
    }

    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cliente cliente)) return false;
        return Objects.equals(id, cliente.id) && Objects.equals(nome, cliente.nome) && Objects.equals(telefone, cliente.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, telefone);
    }
}
