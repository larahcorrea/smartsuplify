package br.com.fiap.smartsuplify.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.smartsuplify.clients.FornecedorClient;
import br.com.fiap.smartsuplify.model.dto.FornecedorDTO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fornecedores")
@Slf4j
public class FornecedorController {

    @Autowired
    FornecedorClient fornecedorClient;

    @GetMapping
    public List<FornecedorDTO> index(){
        log.info("buscando todos os fornecedores");
        return fornecedorClient.getFornecedores().getBody();
    }
    
}
