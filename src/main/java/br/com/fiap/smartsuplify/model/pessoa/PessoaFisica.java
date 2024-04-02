package br.com.fiap.smartsuplify.model.pessoa;

import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TB_PF", uniqueConstraints = {
        @UniqueConstraint(name = "UK_PF_CPF", columnNames = "NR_CPF")
})
@DiscriminatorValue("PF")
public class PessoaFisica extends Pessoa implements UserDetails{

    @Column(name = "NR_CPF", nullable = false)
    @NotBlank
    private String cpf;

    @Column(name = "EMAIL")
    @NotBlank
    private String email;

    @Column(name = "SENHA")
    @NotBlank
    private String senha;
    
    public PessoaFisica(Long id, String nome, String cpf, String email, String senha) {
        super(id, nome);
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, null, getAuthorities());
    }
}
