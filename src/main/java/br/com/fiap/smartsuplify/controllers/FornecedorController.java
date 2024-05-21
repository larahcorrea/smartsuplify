package br.com.fiap.smartsuplify.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.smartsuplify.clients.FornecedorAutenticacaoClient;
import br.com.fiap.smartsuplify.clients.FornecedorClient;
import br.com.fiap.smartsuplify.model.dto.FornecedorDTO;
import br.com.fiap.smartsuplify.model.dto.UsuarioDTO;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/fornecedores")
@Slf4j
public class FornecedorController {

    @Autowired
    FornecedorClient fornecedorClient ;

    @Autowired
    FornecedorAutenticacaoClient autentica;

    @GetMapping
    public List<FornecedorDTO> index(){

        log.info("buscando todos os fornecedores");

        List<String> dados = geraToken();
        log.info(dados.get(0));
        log.info(dados.get(1));
        return fornecedorClient.getFornecedores(dados.get(0), dados.get(1));
    }

    private List<String> geraToken() {
        UsuarioDTO usuarioDTO = new UsuarioDTO("ana@email.com", "Te$teBra$il5", "Te$teBra$il5");
        var token = autentica.getToken(usuarioDTO);

        List<String> acessos = new ArrayList<>();

        var headers = token.getHeaders();
        var cookies = headers.get("Set-Cookie");
        for(String cookie: cookies ){
            log.info(cookie);
            acessos.add(cookie);
        }

        String primeiroCookie = acessos.get(0);
        String segundoCookie = acessos.get(1);

        int indicePontoEVirgula = primeiroCookie.indexOf(';');
        String primeiroCookienovo = primeiroCookie.substring(0, indicePontoEVirgula);

        indicePontoEVirgula = segundoCookie.indexOf(';');
        String segundoCookienovo = segundoCookie.substring(0, indicePontoEVirgula);

        List<String> dadosLogin = new ArrayList<>();

        log.info(token.getBody().token());

        dadosLogin.add(token.getBody().token());
        dadosLogin.add(segundoCookienovo + ";" + primeiroCookienovo);


        return dadosLogin;

        
    }
    
    
}
