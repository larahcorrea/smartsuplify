package br.com.fiap.smartsuplify.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.fiap.smartsuplify.model.dto.EmpresaDto;

@Component
public class EmpresaClient {

    @Autowired
    RestTemplate restTemplate;

    String URL_EMPRESAS = "http://localhost:5119/api/Empresa";

    public ResponseEntity<List<EmpresaDto>> getEmpresas(){
        ParameterizedTypeReference<List<EmpresaDto>> responseType = new ParameterizedTypeReference<List<EmpresaDto>>() {};
        return restTemplate.exchange(URL_EMPRESAS, HttpMethod.GET, null, responseType);

    }
    
}
