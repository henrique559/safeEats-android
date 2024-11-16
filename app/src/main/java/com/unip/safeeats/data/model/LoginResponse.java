package com.unip.safeeats.data.model;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private String token;
    private Cliente cliente;

    public LoginResponse(String token, Cliente cliente) {
        this.token = token;
        this.cliente = cliente;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
