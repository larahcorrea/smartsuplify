package br.com.fiap.smartsuplify.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.smartsuplify.model.dto.FornecedorDTO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FornecedorClient {

    @Autowired
    RestTemplate restTemplate;

    String URL_FORNECEDORES = "http://localhost:5202/api/Fornecedor";

    public ResponseEntity<List<FornecedorDTO>> getFornecedores(){
        ParameterizedTypeReference<List<FornecedorDTO>> responseType = new ParameterizedTypeReference<List<FornecedorDTO>>() {};
        return restTemplate.exchange(URL_FORNECEDORES, HttpMethod.GET, null, responseType);

    }
    
}
