package br.com.fiap.smartsuplify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.smartsuplify.model.Solicitacao;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    
}
