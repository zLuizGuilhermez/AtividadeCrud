package com.AtividadeCrud.example.demo.dto;

public class UfDto {

    private Integer id;
    private String nome;
    private String sigla;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId(){ return id; }
}
