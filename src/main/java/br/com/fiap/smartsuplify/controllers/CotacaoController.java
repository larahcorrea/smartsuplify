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
import br.com.fiap.smartsuplify.model.Cotacao;
import br.com.fiap.smartsuplify.repository.CotacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/cotacoes")
@Slf4j
public class CotacaoController {
    
    @Autowired
    CotacaoRepository cotacaoRepository;
    
    @GetMapping
    public Page<Cotacao> index(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest){
        log.info("buscando todos os cotacaos");
        return cotacaoRepository.findAll(pageRequest);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cotacao> show(@PathVariable Long id){
        log.info("mostrar cotacao com id " + id);
        return ResponseEntity.ok(getCotacaoById(id));
    }

    @PostMapping
    public ResponseEntity<Cotacao> create(@RequestBody @Valid Cotacao cotacao){
        log.info("cadastrando cotacao: " + cotacao);
        cotacaoRepository.save(cotacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(cotacao);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cotacao> update(@PathVariable Long id, @RequestBody @Valid Cotacao cotacao){
        log.info("atualizando dados do cotacao com id " + id);

        getCotacaoById(id);

        cotacao.setId(id);
        cotacaoRepository.save(cotacao);

        return ResponseEntity.ok(cotacao);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cotacao> destroy(@PathVariable Long id){
        log.info("apagando cotacao com id " + id);

        cotacaoRepository.delete(getCotacaoById(id));

        return ResponseEntity.noContent().build();
    }

    private Cotacao getCotacaoById(Long id){
        return cotacaoRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }
}
