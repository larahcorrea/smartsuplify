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
@Table(name = "TB_SERVICO")
@DiscriminatorValue("SERVICO")
public class Servico extends Adquirivel{

    @Column(name = "CARGA_HORARIA_SERVICO")
    private String cargaHoraria;

    public Servico(Long id, String nome, String cargaHoraria) {
        super( id, nome);
        this.cargaHoraria = cargaHoraria;
    }
    
}
