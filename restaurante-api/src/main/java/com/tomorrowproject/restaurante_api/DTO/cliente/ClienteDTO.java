package com.tomorrowproject.restaurante_api.DTO.cliente;

import com.tomorrowproject.restaurante_api.DTO.pedido.PedidoDTO;
import com.tomorrowproject.restaurante_api.entity.Pedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ClienteDTO {


    @NotNull(message = "o nome é invalido")
    @NotEmpty(message = "o nome é invalido")
    @NotBlank(message = "o nome é invalido")
    private String nome;

    @NotNull(message = "o telefone é invalido")
    @NotEmpty(message = "o telefone é invalido")
    @NotBlank(message = "o telefone é invalido")
    private String telefone;

    @NotNull(message = "o endereço é invalido")
    @NotEmpty(message = "o endereço é invalido")
    @NotBlank(message = "o endereço é invalido")
    private String endereco;

    public ClienteDTO() {
    }

    public ClienteDTO(String nome, String telefone, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
