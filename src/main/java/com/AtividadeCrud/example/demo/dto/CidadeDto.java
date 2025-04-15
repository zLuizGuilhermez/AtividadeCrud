package com.AtividadeCrud.example.demo.dto;

public class CidadeDto {
    private String nome;
    private String nomeAtual;
    private String nomeAntigo;

    public String getNomeAtual() {
        return nomeAtual;
    }

    public void setNomeAtual(String nomeAtual) {
        this.nomeAtual = nomeAtual;
    }

    public String getNomeAntigo() {
        return nomeAntigo;
    }

    public void setNomeAntigo(String nomeAntigo) {
        this.nomeAntigo = nomeAntigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
