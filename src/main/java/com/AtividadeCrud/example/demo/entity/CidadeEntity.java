package com.AtividadeCrud.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cidade")
public class CidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "uf_id", nullable = false)
    @JsonIgnore
    private UfEntity uf;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<EstudanteEntity> estudantes;

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

    public UfEntity getUf() {
        return uf;
    }

    public void setUf(UfEntity uf) {
        this.uf = uf;
    }

    public List<EstudanteEntity> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<EstudanteEntity> estudantes) {
        this.estudantes = estudantes;
    }
}
