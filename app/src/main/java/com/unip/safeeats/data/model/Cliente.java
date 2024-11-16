package com.unip.safeeats.data.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Cliente implements Serializable {
    private Usuario usuario;
    private Endereco endereco;
    private String cpf;
    private String telefone;

    public Cliente() {
    }

    public Cliente(Usuario usuario, Endereco enderecos, String cpf, String telefone) {
        this.usuario = usuario;
        this.endereco = enderecos;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }



    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                ", usuario=" + usuario +
                ", enderecos=" + endereco +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
