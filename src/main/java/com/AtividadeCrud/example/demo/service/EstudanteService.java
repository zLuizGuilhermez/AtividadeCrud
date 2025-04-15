package com.AtividadeCrud.example.demo.service;

import com.AtividadeCrud.example.demo.entity.EstudanteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EstudanteService {

    @Autowired
    CidadeService cidadeService;



    void criarEstudante(String nome, String matricula, String email, LocalDate dtNascimento){


        EstudanteEntity estudanteEntity = new EstudanteEntity();
        estudanteEntity.setNome(nome);
        estudanteEntity.setEmail(email);
        estudanteEntity.setMatricula(matricula);
        estudanteEntity.setDtNascimento(dtNascimento);


    }
}
