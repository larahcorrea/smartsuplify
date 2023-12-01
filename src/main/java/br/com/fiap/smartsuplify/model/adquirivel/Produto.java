package br.com.fiap.smartsuplify.model.adquirivel;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "TB_PRODUTO")
@DiscriminatorValue("PRODUTO")
public class Produto extends Adquirivel{

    @Column(name = "TAMANHO_PRODUTO")
    private String tamanho;

    public Produto(Long id, String nome, String tamanho) {
        super( id, nome);
        this.tamanho = tamanho;
    }

    
}
