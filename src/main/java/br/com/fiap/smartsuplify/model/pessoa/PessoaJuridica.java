package br.com.fiap.smartsuplify.model.pessoa;

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
@Table(name = "TB_PJ", uniqueConstraints = {
        @UniqueConstraint(name = "UK_PJ_CNPJ", columnNames = "NR_CNPJ")
})
@DiscriminatorValue("PJ")
public class PessoaJuridica extends Pessoa{

    @Column(name = "NR_CNPJ", nullable = false)
    @NotBlank
    private String cnpj;

     public PessoaJuridica(Long id, String nome, String email, String password, String cnpj) {
        super( id, nome, email, password );
        this.cnpj = cnpj;
    }
}
