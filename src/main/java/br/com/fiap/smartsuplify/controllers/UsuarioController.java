package br.com.fiap.smartsuplify.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.smartsuplify.model.dto.Credenciais;
import br.com.fiap.smartsuplify.model.dto.Token;
import br.com.fiap.smartsuplify.model.dto.UsuarioResponse;
import br.com.fiap.smartsuplify.model.pessoa.PessoaFisica;
import br.com.fiap.smartsuplify.repository.PessoaFisicaRepository;
import br.com.fiap.smartsuplify.services.TokenService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class UsuarioController {

    @Autowired
    TokenService tokenService;

    @Autowired
    PessoaFisicaRepository repository;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<Token> login(@RequestBody Credenciais credenciais){
        log.info(credenciais);
        authManager.authenticate(credenciais.toAuthentication());
        log.info("logou");
        return ResponseEntity.ok( tokenService.generateToken(credenciais.email()) );
    }
    
    @PostMapping("/usuario")
    public ResponseEntity<UsuarioResponse> create(@RequestBody @Valid PessoaFisica usuario){
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        repository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.fromUsuario(usuario));
    }

    
}
