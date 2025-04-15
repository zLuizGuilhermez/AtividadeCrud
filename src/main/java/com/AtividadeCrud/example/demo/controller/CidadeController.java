package com.AtividadeCrud.example.demo.controller;

import com.AtividadeCrud.example.demo.entity.CidadeEntity;
import com.AtividadeCrud.example.demo.entity.EstudanteEntity;
import com.AtividadeCrud.example.demo.service.CidadeService;
import com.AtividadeCrud.example.demo.dto.CidadeDto;
import org.hibernate.Remove;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cidade")
public class CidadeController {

    @Autowired
    CidadeService cidadeService;

    @PostMapping("/criarcidade/{sigla}")
    public void setCidade(@RequestBody CidadeDto cidadeDto, @PathVariable String sigla){
        cidadeService.criarCidade(cidadeDto.getNome(),sigla);
    }

    @PutMapping("/alterarCidade")
    public String alteraInstanciaCidade(@RequestBody CidadeDto cidadeDto){
        cidadeService.alterarCidade(cidadeDto.getNomeAntigo(), cidadeDto.getNomeAtual());
        return "Cidade Alterada! " + cidadeDto.getNomeAtual();

    }

    @GetMapping("/getCidade/{nome}")
    public CidadeEntity getCidade(@PathVariable String nome){
        return cidadeService.buscarCidadePorNome(nome);
    }

    @DeleteMapping("/deletarCidade/{nome}")
    public String removerCidade(@PathVariable String nome){
         cidadeService.removerCidade(nome);
         return "removido com sucesso";
    }


}
