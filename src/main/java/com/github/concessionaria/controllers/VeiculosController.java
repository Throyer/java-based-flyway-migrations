package com.github.concessionaria.controllers;

import com.github.concessionaria.models.Veiculo;
import com.github.concessionaria.repositories.VeiculosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/veiculos")
@RestController()
public class VeiculosController {

    @Autowired
    private VeiculosRepository repository;

    @GetMapping()
    public Page<Veiculo> index(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
