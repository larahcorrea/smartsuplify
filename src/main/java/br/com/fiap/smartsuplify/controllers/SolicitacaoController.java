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
import br.com.fiap.smartsuplify.model.Solicitacao;
import br.com.fiap.smartsuplify.repository.SolicitacaoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/solicitacoes")
@Slf4j
public class SolicitacaoController {
    
    @Autowired
    SolicitacaoRepository solicitacaoRepository;
    
    @GetMapping
    public Page<Solicitacao> index(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest){
        log.info("buscando todos os solicitacaos");
        return solicitacaoRepository.findAll(pageRequest);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Solicitacao> show(@PathVariable Long id){
        log.info("mostrar solicitacao com id " + id);
        return ResponseEntity.ok(getSolicitacaoById(id));
    }

    @PostMapping
    public ResponseEntity<Solicitacao> create(@RequestBody @Valid Solicitacao solicitacao){
        log.info("cadastrando solicitacao: " + solicitacao);
        solicitacaoRepository.save(solicitacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitacao);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Solicitacao> update(@PathVariable Long id, @RequestBody @Valid Solicitacao solicitacao){
        log.info("atualizando dados do solicitacao com id " + id);

        getSolicitacaoById(id);

        solicitacao.setId(id);
        solicitacaoRepository.save(solicitacao);

        return ResponseEntity.ok(solicitacao);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Solicitacao> destroy(@PathVariable Long id){
        log.info("apagando solicitacao com id " + id);

        solicitacaoRepository.delete(getSolicitacaoById(id));

        return ResponseEntity.noContent().build();
    }

    private Solicitacao getSolicitacaoById(Long id){
        return solicitacaoRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }
}
