package com.AtividadeCrud.example.demo.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "uf")
public class UfEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String sigla;

    @OneToMany(mappedBy = "uf", cascade = CascadeType.ALL)
    private List<CidadeEntity> cidades;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<CidadeEntity> getCidades() {
        return cidades;
    }

    public void setCidades(List<CidadeEntity> cidades) {
        this.cidades = cidades;
    }
}
