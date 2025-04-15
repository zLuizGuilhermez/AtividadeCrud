package com.AtividadeCrud.example.demo.controller;

import com.AtividadeCrud.example.demo.entity.CidadeEntity;
import com.AtividadeCrud.example.demo.entity.EstudanteEntity;
import com.AtividadeCrud.example.demo.service.CidadeService;
import com.AtividadeCrud.example.demo.dto.CidadeDto;
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
    void setCidade(@RequestBody CidadeDto cidadeDto, @PathVariable String sigla){
        cidadeService.criarCidade(cidadeDto.getNome(),sigla);
    }

    @PutMapping("/alterarCidade")
    String alteraInstanciaCidade(@RequestBody CidadeDto cidadeDto){
        //try{
        cidadeService.alterarCidade(cidadeDto.getNomeAntigo(), cidadeDto.getNomeAtual());
        return "Cidade Alterada! " + cidadeDto.getNomeAtual();
        //} catch (ChangeSetPersister.NotFoundException e) {
          //  throw new (e);
       // }
    }

    @GetMapping("/getCidade/{nome}")
    CidadeEntity getCidade(@PathVariable String nome){
        return cidadeService.buscarCidadePorNome(nome);
    }


}
