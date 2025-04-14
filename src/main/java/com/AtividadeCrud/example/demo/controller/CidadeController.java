package com.AtividadeCrud.example.demo.controller;

import com.AtividadeCrud.example.demo.Service.CidadeService;
import com.AtividadeCrud.example.demo.dto.CidadeDto;
import com.AtividadeCrud.example.demo.dto.UfDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("Cidade")
public class CidadeController {

    @Autowired
    CidadeService cidadeService;

    @PostMapping("Criarcidade/{sigla}")
    void criarCidade(@RequestBody CidadeDto cidadeDto, @PathVariable String sigla){
        cidadeService.criarCidade(cidadeDto.getNome(),sigla);
    }
}
