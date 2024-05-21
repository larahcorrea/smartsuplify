package br.com.fiap.smartsuplify.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.smartsuplify.model.dto.TokenDTO;
import br.com.fiap.smartsuplify.model.dto.UsuarioDTO;

@FeignClient(url = "http://localhost:5202/api/Autoriza/Login", name = "autentica")
public interface FornecedorAutenticacaoClient {

    @PostMapping
    ResponseEntity<TokenDTO> getToken(@RequestBody UsuarioDTO usuarioDTO);
    
}
