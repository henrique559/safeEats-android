package com.unip.safeeats.data.model;

import java.io.Serializable;

public class CarrinhoItem implements Serializable {
    private int quantidade;
    private double precoUnitario;
    private double subtotal;
    private Produto produto;

    public CarrinhoItem(int quantidade, double precoUnitario, double subtotal, Produto produto) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
        this.produto = produto;
    }

    public CarrinhoItem() {
    }

    // Getters e Setters

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public double getSubtotal() {
        return precoUnitario * quantidade ;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}