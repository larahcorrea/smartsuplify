package br.com.fiap.smartsuplify.model.dto;

public record TokenDTO(boolean authenticated, String expiration, String token, String message ) {
} 
