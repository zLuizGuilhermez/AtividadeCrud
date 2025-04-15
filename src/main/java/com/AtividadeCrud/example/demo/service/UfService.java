package com.AtividadeCrud.example.demo.service;

import com.AtividadeCrud.example.demo.repository.UfRepository;
import com.AtividadeCrud.example.demo.entity.UfEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UfService {

    @Autowired
    UfRepository ufRepository;


    public void criarUf(String nome, String sigla){
        UfEntity ufEntity = new UfEntity();
        ufEntity.setNome(nome);
        ufEntity.setSigla(sigla);
        ufRepository.save(ufEntity);
    }

    public void editarUf(){
        // Implemente a lógica de edição aqui
    }

    public UfEntity buscarUfPorSigla(String sigla) {
        return ufRepository.findFirstBySigla(sigla)
                .orElseThrow(() -> new RuntimeException("UF não encontrada"));
    }
}

