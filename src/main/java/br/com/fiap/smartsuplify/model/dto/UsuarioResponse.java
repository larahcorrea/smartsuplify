package br.com.fiap.smartsuplify.model.dto;

import br.com.fiap.smartsuplify.model.pessoa.PessoaFisica;

public record UsuarioResponse (String nome, String email){

    public static UsuarioResponse fromUsuario(PessoaFisica usuario){
        return new UsuarioResponse(usuario.getNome(), usuario.getEmail());
    }
    
}
