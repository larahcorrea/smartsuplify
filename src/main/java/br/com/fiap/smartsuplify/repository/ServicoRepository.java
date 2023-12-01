package br.com.fiap.smartsuplify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.smartsuplify.model.adquirivel.Servico;


public interface ServicoRepository extends JpaRepository<Servico, Long>{
    
}
