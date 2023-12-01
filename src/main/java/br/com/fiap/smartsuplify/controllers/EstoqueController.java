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
import br.com.fiap.smartsuplify.model.Estoque;
import br.com.fiap.smartsuplify.repository.EstoqueRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/estoques")
@Slf4j
public class EstoqueController {

    @Autowired
    EstoqueRepository estoqueRepository;
    
    @GetMapping
    public Page<Estoque> index(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest){
        log.info("buscando todos os estoques");
        return estoqueRepository.findAll(pageRequest);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Estoque> show(@PathVariable Long id){
        log.info("mostrar estoque com id " + id);
        return ResponseEntity.ok(getEstoqueById(id));
    }

    @PostMapping
    public ResponseEntity<Estoque> create(@RequestBody @Valid Estoque estoque){
        log.info("cadastrando estoque: " + estoque);
        estoqueRepository.save(estoque);
        return ResponseEntity.status(HttpStatus.CREATED).body(estoque);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Estoque> update(@PathVariable Long id, @RequestBody @Valid Estoque estoque){
        log.info("atualizando dados do estoque com id " + id);

        getEstoqueById(id);

        estoque.setId(id);
        estoqueRepository.save(estoque);

        return ResponseEntity.ok(estoque);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Estoque> destroy(@PathVariable Long id){
        log.info("apagando estoque com id " + id);

        estoqueRepository.delete(getEstoqueById(id));

        return ResponseEntity.noContent().build();
    }

    private Estoque getEstoqueById(Long id){
        return estoqueRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }

    
}
