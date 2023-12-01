package br.com.fiap.smartsuplify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.smartsuplify.model.pessoa.PessoaFisica;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
    
}
