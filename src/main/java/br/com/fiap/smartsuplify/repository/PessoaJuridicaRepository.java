package br.com.fiap.smartsuplify.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.smartsuplify.model.pessoa.PessoaJuridica;

public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long>{

    Optional<PessoaJuridica> findByCnpj(String cnpj);
    
}
