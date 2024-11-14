package com.unip.safeeats.data.DTO;

public class RegistroDTO {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private EnderecoDTO endereco;

    public RegistroDTO(String nome, String email, String senha, String cpf, String telefone, EnderecoDTO endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public RegistroDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public EnderecoDTO getEnderecos() {
        return endereco;
    }

    public void setEnderecos(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "RegistroDTO{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
