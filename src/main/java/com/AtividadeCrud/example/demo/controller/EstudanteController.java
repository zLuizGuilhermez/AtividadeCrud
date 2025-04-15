package com.AtividadeCrud.example.demo.controller;

import com.AtividadeCrud.example.demo.service.EstudanteService;
import com.AtividadeCrud.example.demo.dto.EstudanteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Estudante")
public class EstudanteController {

    @Autowired
    EstudanteService estudanteService;

    @PostMapping("criarEstudante/{nome}")
    void criarEstudante(@RequestBody EstudanteDto estudanteDto, @PathVariable String nomeCidade){
        estudanteService.criarEstudante(estudanteDto.getNome(), estudanteDto.getMatricula(), estudanteDto.getEmail(), nomeCidade)
    }



}
