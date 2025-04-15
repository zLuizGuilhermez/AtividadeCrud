package com.AtividadeCrud.example.demo.controller;

import com.AtividadeCrud.example.demo.entity.UfEntity;
import com.AtividadeCrud.example.demo.service.UfService;
import com.AtividadeCrud.example.demo.dto.UfDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/uf")
public class UfController {

    @Autowired
    UfService ufService;

    @PostMapping("/criarUf")
    public void criarUf(@RequestBody UfDto ufDto) {
            ufService.criarUf(ufDto.getNome(), ufDto.getSigla());
    }

    @PutMapping("/alterarUf")
    public void alterarUf(@RequestBody UfDto ufDto){ ufService.editarUf(ufDto.getId(), ufDto.getNome(), ufDto.getSigla()); }

    @GetMapping("/buscarUf/{sigla}")
    public UfEntity buscarUf(@PathVariable String sigla){ return ufService.buscarUfPorSigla(sigla); }

    @DeleteMapping("/removerUf/{sigla}")
    public String deletarUf(@PathVariable String sigla){
        ufService.removerUf(sigla);
        return "removido com sucesso";
    }
}
