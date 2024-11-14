package com.unip.safeeats.data.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Cliente implements Serializable {
    private Usuario usuario;
    private List<Endereco> enderecos = new ArrayList<>();
    private String cpf;
    private String telefone;

    public Cliente(String cpf, String telefone, Usuario usuario, Endereco endereco) {
        this.usuario = usuario;
        this.cpf = cpf;
        this.telefone = telefone;
        enderecos.add(endereco);
    }

    public Cliente() {
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
                ", enderecos=" + enderecos.get(0) +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
