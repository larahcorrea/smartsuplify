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
import br.com.fiap.smartsuplify.model.adquirivel.Produto;
import br.com.fiap.smartsuplify.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/produtos")
@Slf4j
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;
    
    @GetMapping
    public Page<Produto> index(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest){
        log.info("buscando todos os produtos");
        return produtoRepository.findAll(pageRequest);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> show(@PathVariable Long id){
        log.info("mostrar produto com id " + id);
        return ResponseEntity.ok(getProdutoById(id));
    }

    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody @Valid Produto produto){
        log.info("cadastrando produto: " + produto);
        produtoRepository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody @Valid Produto produto){
        log.info("atualizando dados do produto com id " + id);

        getProdutoById(id);

        produto.setId(id);
        produtoRepository.save(produto);

        return ResponseEntity.ok(produto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id){
        log.info("apagando produto com id " + id);

        produtoRepository.delete(getProdutoById(id));

        return ResponseEntity.noContent().build();
    }

    private Produto getProdutoById(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }

    
}
