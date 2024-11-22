package com.unip.safeeats.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Fornecedor implements Parcelable {
    private Usuario usuario;
    private Endereco endereco;
    private String cnpj;
    private String tipoProduto;
    private String razaoSocial;
    private String nomeFantasia;

    // Construtor completo
    public Fornecedor(Usuario usuario, Endereco endereco, String cnpj, String tipoProduto, String razaoSocial, String nomeFantasia) {
        this.usuario = usuario;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.tipoProduto = tipoProduto;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    // Construtor padrão
    public Fornecedor() {}

    // Construtor Parcelable
    protected Fornecedor(Parcel in) {
        usuario = in.readParcelable(Usuario.class.getClassLoader());
        endereco = in.readParcelable(Endereco.class.getClassLoader());
        cnpj = in.readString();
        tipoProduto = in.readString();
        razaoSocial = in.readString();
        nomeFantasia = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cnpj);
        dest.writeString(usuario.getNome());
        dest.writeString(endereco.getRua());
        dest.writeString(tipoProduto);
        dest.writeString(razaoSocial);
        dest.writeString(nomeFantasia);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Fornecedor> CREATOR = new Creator<Fornecedor>() {
        @Override
        public Fornecedor createFromParcel(Parcel in) {
            return new Fornecedor(in);
        }

        @Override
        public Fornecedor[] newArray(int size) {
            return new Fornecedor[size];
        }
    };

    // Getters e setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
}
