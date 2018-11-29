package com.example.a47894359890.projetobrq.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Projeto implements Serializable{
    private String nome;
    private Long id;
//    private Date dataInicio;
//    private Date dataFinal;
    private int horas;
    private String responsavelBRQ;
    private String responsavelCliente;
    private String descricao;
    // private Tecnologias tecnologia;
    private List<Tecnologias> tecnologia;
    private Status status;
    private Usuario usuarioCriador;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getResponsavelBRQ() {
        return responsavelBRQ;
    }

    public void setResponsavelBRQ(String responsavelBRQ) {
        this.responsavelBRQ = responsavelBRQ;
    }

    public String getResponsavelCliente() {
        return responsavelCliente;
    }

    public void setResponsavelCliente(String responsavelCliente) {
        this.responsavelCliente = responsavelCliente;
    }

    public List<Tecnologias> getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(List<Tecnologias> tecnologia) {
        this.tecnologia = tecnologia;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Usuario getUsuarioCriador() {
        return usuarioCriador;
    }

    public void setUsuarioCriador(Usuario usuarioCriador) {
        this.usuarioCriador = usuarioCriador;
    }
}
