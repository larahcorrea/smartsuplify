package br.com.fiap.smartsuplify.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.fiap.smartsuplify.model.dto.Token;
import br.com.fiap.smartsuplify.model.pessoa.PessoaFisica;
import br.com.fiap.smartsuplify.repository.PessoaFisicaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TokenService {

    @Autowired
    PessoaFisicaRepository pessoaFisicaRepository;

     public Token generateToken(String email){

        Algorithm algorithm = Algorithm.HMAC512("meusecretsupersecreto");

        var jwt = JWT.create().withIssuer("smartsuplify")
        .withSubject(email)
        .withExpiresAt(Instant.now().plus(10, ChronoUnit.MINUTES))
        .sign(algorithm);

        return new Token(jwt, "JWT", "Bearer");

    }

     public PessoaFisica validateToken(String token){

        Algorithm algorithm = Algorithm.HMAC512("meusecretsupersecreto");

        String email = JWT.require(algorithm)
                        .withIssuer("smartsuplify")
                        .build()
                        .verify(token)
                        .getSubject();
        
        log.info("email token: " + email);

        return pessoaFisicaRepository.findByEmail(email).orElseThrow(() -> new JWTVerificationException("Erro na verificação do Token"));
    }
    
}
