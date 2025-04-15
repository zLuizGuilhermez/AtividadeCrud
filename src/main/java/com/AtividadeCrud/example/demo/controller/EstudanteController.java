package com.AtividadeCrud.example.demo.controller;

import com.AtividadeCrud.example.demo.entity.EstudanteEntity;
import com.AtividadeCrud.example.demo.service.EstudanteService;
import com.AtividadeCrud.example.demo.dto.EstudanteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estudante")
public class EstudanteController {

    @Autowired
    EstudanteService estudanteService;

    @GetMapping("/getEstudante/{matricula}")
    EstudanteEntity getEstudante(@PathVariable String matricula){
       return estudanteService.getEstudante(matricula);
    }

    @PostMapping("/criarEstudante")
    void criarEstudante(@RequestBody EstudanteDto estudanteDto){
        estudanteService.criarEstudante(estudanteDto.getNome(), estudanteDto.getMatricula(), estudanteDto.getEmail(),estudanteDto.getDtNascimento(), estudanteDto.getNomeCidade());
    }

    @PutMapping("/alterarEstudante")
    void alterarEstudante(@RequestBody EstudanteDto estudanteDto){
        estudanteService.alterarEstudante(estudanteDto.getMatricula(), estudanteDto.getNome(), estudanteDto.getEmail());
    }

    @DeleteMapping("/deletarEstudante/{matricula}")
    public String removerEstudante(@PathVariable String matricula){
        estudanteService.removerEstudante(matricula);
        return "removido com sucesso";
    }


}
