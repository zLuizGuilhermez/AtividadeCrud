package com.AtividadeCrud.example.demo.Service;

import com.AtividadeCrud.example.demo.Repository.CidadeRepository;
import com.AtividadeCrud.example.demo.entity.CidadeEntity;
import com.AtividadeCrud.example.demo.entity.UfEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UfEntity buscarCidadePorNome(String nome) {
        return ufRepository.findFirstBySigla(sigla)
                .orElseThrow(() -> new RuntimeException("UF não encontrada"));
    }
}
