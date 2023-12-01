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
import br.com.fiap.smartsuplify.model.adquirivel.Servico;
import br.com.fiap.smartsuplify.repository.ServicoRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/servicos")
@Slf4j
public class ServicoController {

    @Autowired
    ServicoRepository servicoRepository;
    
    @GetMapping
    public Page<Servico> index(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageRequest){
        log.info("buscando todos os servicos");
        return servicoRepository.findAll(pageRequest);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Servico> show(@PathVariable Long id){
        log.info("mostrar servico com id " + id);
        return ResponseEntity.ok(getServicoById(id));
    }

    @PostMapping
    public ResponseEntity<Servico> create(@RequestBody @Valid Servico servico){
        log.info("cadastrando servico: " + servico);
        servicoRepository.save(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(servico);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Servico> update(@PathVariable Long id, @RequestBody @Valid Servico servico){
        log.info("atualizando dados do servico com id " + id);

        getServicoById(id);

        servico.setId(id);
        servicoRepository.save(servico);

        return ResponseEntity.ok(servico);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id){
        log.info("apagando servico com id " + id);

        servicoRepository.delete(getServicoById(id));

        return ResponseEntity.noContent().build();
    }

    private Servico getServicoById(Long id){
        return servicoRepository.findById(id).orElseThrow(() -> { 
             return new ResourceNotFoundException("Entidade não encontrada");
         });
     }
    
}
