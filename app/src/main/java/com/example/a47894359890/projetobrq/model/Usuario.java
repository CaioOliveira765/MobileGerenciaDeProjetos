package com.example.a47894359890.projetobrq.model;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    private Long id;
    private String nome;
    private String sobrenome;
    private String Cargo;
    @SerializedName("email")
    private String email;
    private int telefone;
    private String senha;
    private String  permissao;


    public Usuario(String emailDigitado, String senhaDigitado) {
        this.email = emailDigitado;
        this.senha = senhaDigitado;
    }

    public Usuario(String emailDigitado) {
        this.email = emailDigitado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String cargo) {
        Cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }
}