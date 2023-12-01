package br.com.fiap.smartsuplify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.smartsuplify.model.pessoa.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
    
}
