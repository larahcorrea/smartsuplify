package br.com.fiap.smartsuplify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.smartsuplify.error.exceptions.ResourceNotFoundException;
import br.com.fiap.smartsuplify.model.pessoa.PessoaFisica;
import br.com.fiap.smartsuplify.repository.PessoaFisicaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/funcionarios")
@Slf4j
public class PessoaFisicaController {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;
    
    @GetMapping
    public Page<PessoaFisica> index(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest){
        log.info("buscando todos os funcionarios");
        return pessoaFisicaRepository.findAll(pageRequest);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PessoaFisica> show(@PathVariable Long id){
        log.info("mostrar funcionario com id " + id);
        return ResponseEntity.ok(getPessoaFisicaById(id));
    }

    @PostMapping
    public ResponseEntity<PessoaFisica> create(@RequestBody @Valid PessoaFisica pessoaFisica){
        log.info("cadastrando funcionario: " + pessoaFisica);
        pessoaFisicaRepository.save(pessoaFisica);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaFisica);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaFisica> update(@PathVariable Long id, @RequestBody @Valid PessoaFisica pessoaFisica){
        log.info("atualizando dados do funcionario com id " + id);

        getPessoaFisicaById(id);

        pessoaFisica.setId(id);
        pessoaFisicaRepository.save(pessoaFisica);

        return ResponseEntity.ok(pessoaFisica);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PessoaFisica> destroy(@PathVariable Long id){
        log.info("apagando funcionario com id " + id);

        pessoaFisicaRepository.delete(getPessoaFisicaById(id));

        return ResponseEntity.noContent().build();
    }

    private PessoaFisica getPessoaFisicaById(Long id){
        return pessoaFisicaRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }
    
}
