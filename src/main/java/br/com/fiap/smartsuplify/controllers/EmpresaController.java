package br.com.fiap.smartsuplify.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.smartsuplify.error.exceptions.ResourceNotFoundException;
import br.com.fiap.smartsuplify.model.pessoa.PessoaJuridica;
import br.com.fiap.smartsuplify.repository.PessoaJuridicaRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/empresas")
@Slf4j
public class EmpresaController {

    @Autowired
    PessoaJuridicaRepository pessoaJuridicaRepository;

    @GetMapping
    public List<PessoaJuridica> index(){
        log.info("buscando todos as empresas");
        return pessoaJuridicaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaJuridica> show(@PathVariable Long id){
        log.info("mostrar empresa com id " + id);
        return ResponseEntity.ok(getPessoaJuridicaById(id));
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaJuridica> show(@PathVariable String cnpj){
        log.info("mostrar PessoaJuridica " + cnpj);
        return ResponseEntity.ok(getPessoaJuridicaByCnpj(cnpj));
    }

    private PessoaJuridica getPessoaJuridicaById(Long id){
        return pessoaJuridicaRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
    }

    private PessoaJuridica getPessoaJuridicaByCnpj(String cnpj){
        return pessoaJuridicaRepository.findByCnpj(cnpj).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
    }
    
}
