package com.AtividadeCrud.example.demo.service;

import com.AtividadeCrud.example.demo.entity.EstudanteEntity;
import com.AtividadeCrud.example.demo.repository.CidadeRepository;
import com.AtividadeCrud.example.demo.entity.CidadeEntity;
import com.AtividadeCrud.example.demo.entity.UfEntity;
import com.AtividadeCrud.example.demo.repository.EstudanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Optional;

@Service
public class CidadeService {


    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    UfService ufService;

    public void criarCidade(String nome, String uf){
        UfEntity ufEntityParaId = ufService.buscarUfPorSigla(uf);
        CidadeEntity cidadeEntity = new CidadeEntity();
        cidadeEntity.setNome(nome);
        cidadeEntity.setUf(ufEntityParaId);
        cidadeRepository.save(cidadeEntity);
    }

    public void alterarCidade(String nomeAtual, String nomeAtualizado) {
        Optional<CidadeEntity> cidadeEditada = cidadeRepository.findFirstByNome(nomeAtual);

        if (cidadeEditada.isPresent()) {
            CidadeEntity cidade = cidadeEditada.get();
            cidade.setNome(nomeAtualizado);
            cidadeRepository.save(cidade);
        } else {
            throw new EntityNotFoundException("Cidade com nome '" + nomeAtual + "' não encontrada.");
        }
    }

    public void removerCidade(String nome){

        Optional<CidadeEntity> cidadeDel = cidadeRepository.findFirstByNome(nome);

        if(cidadeDel.isPresent()){
            CidadeEntity cidadeEntity = cidadeDel.get();
            cidadeRepository.delete(cidadeEntity);

        }
    }

    public CidadeEntity buscarCidadePorNome(String nome) {
        return cidadeRepository.findFirstByNome(nome)
                .orElseThrow(() -> new RuntimeException("UF não encontrada"));
    }
}
