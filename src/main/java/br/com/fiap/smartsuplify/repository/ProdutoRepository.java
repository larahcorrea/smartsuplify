package br.com.fiap.smartsuplify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.smartsuplify.model.adquirivel.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
}
