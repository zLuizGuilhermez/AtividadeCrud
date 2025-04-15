package com.AtividadeCrud.example.demo.service;

import com.AtividadeCrud.example.demo.entity.CidadeEntity;
import com.AtividadeCrud.example.demo.entity.EstudanteEntity;
import com.AtividadeCrud.example.demo.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class EstudanteService {

    @Autowired
    CidadeService cidadeService;
    @Autowired
    EstudanteRepository estudanteRepository;

    public EstudanteEntity getEstudante(String matricula){
        Optional<EstudanteEntity> resultadoFind = estudanteRepository.findFirstByMatricula(matricula);
        if (resultadoFind.isPresent()) {
            EstudanteEntity estudanteEntity = resultadoFind.get();

            return estudanteEntity;
        }
        return null;
    }

    public void criarEstudante(String nome, String matricula, String email, LocalDate dtNascimento, String nomeCidade){
        CidadeEntity cidadeEntity = cidadeService.buscarCidadePorNome(nomeCidade);

        EstudanteEntity estudanteEntity = new EstudanteEntity();
        estudanteEntity.setNome(nome);
        estudanteEntity.setEmail(email);
        estudanteEntity.setMatricula(matricula);
        estudanteEntity.setDtNascimento(dtNascimento);
        estudanteEntity.setCidade(cidadeEntity);

        estudanteRepository.save(estudanteEntity);
    }

    public void alterarEstudante(String matricula, String nome, String email){
        Optional<EstudanteEntity> resultadoFind = estudanteRepository.findFirstByMatricula(matricula);

        if (resultadoFind.isPresent()){
            EstudanteEntity estudanteEntity = resultadoFind.get();
            estudanteEntity.setEmail(email);
            estudanteEntity.setNome(nome);
            estudanteRepository.save(estudanteEntity);
        }


    }

    public void removerEstudante(String matricula) {

        Optional<EstudanteEntity> resultadoFind = estudanteRepository.findFirstByMatricula(matricula);

        if (resultadoFind.isPresent()) {
            EstudanteEntity estudanteEntity = resultadoFind.get();
            estudanteRepository.delete(estudanteEntity);

        }

    }
}
