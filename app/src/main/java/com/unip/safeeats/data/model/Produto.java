package com.unip.safeeats.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Produto implements Parcelable {
    private Fornecedor fornecedor;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
    private Integer imgID;

    // Construtor padrão
    public Produto() {}

    // Construtor completo
    public Produto(Fornecedor fornecedor, String nome, String descricao, Double preco, Integer quantidade) {
        this.fornecedor = fornecedor;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto(Fornecedor fornecedor, String nome, String descricao, Double preco, Integer quantidade, Integer imgID) {
        this.fornecedor = fornecedor;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.imgID = imgID;
    }

    // Construtor para Parcelable
    protected Produto(Parcel in) {
        fornecedor = in.readParcelable(Fornecedor.class.getClassLoader());
        nome = in.readString();
        descricao = in.readString();
        if (in.readByte() == 0) {
            preco = null;
        } else {
            preco = in.readDouble();
        }
        if (in.readByte() == 0) {
            quantidade = null;
        } else {
            quantidade = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(fornecedor, flags);
        dest.writeString(nome);
        dest.writeString(descricao);
        if (preco == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(preco);
        }
        if (quantidade == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quantidade);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    // Getters e setters
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getImgID() {
        return imgID;
    }

    public void setImgID(Integer imgID) {
        this.imgID = imgID;
    }
}
