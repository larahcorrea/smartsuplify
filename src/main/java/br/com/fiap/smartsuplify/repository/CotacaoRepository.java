package br.com.fiap.smartsuplify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.smartsuplify.model.Cotacao;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long>{
    
}
