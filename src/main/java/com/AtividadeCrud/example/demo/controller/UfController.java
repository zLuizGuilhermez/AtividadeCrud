package com.AtividadeCrud.example.demo.controller;

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
}
