package br.com.fiap.smartsuplify.clients;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.fiap.smartsuplify.model.dto.FornecedorDTO;

@FeignClient(url = "http://localhost:5202/api/Fornecedor", name = "fornecedor")
public interface FornecedorClient {


    @GetMapping
    List<FornecedorDTO> getFornecedores(@RequestHeader("Authorization") String token, @RequestHeader("Cookie") String cookie);
    
}
