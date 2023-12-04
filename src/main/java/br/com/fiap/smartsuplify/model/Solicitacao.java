package br.com.fiap.smartsuplify.model;

import br.com.fiap.smartsuplify.model.adquirivel.Adquirivel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_SOLICITACAO")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_SOLICITACAO")
    @SequenceGenerator(name = "SQ_SOLICITACAO", sequenceName = "SQ_SOLICITACAO", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_SOLICITACAO")
    private Long id;

    @Column(name = "QUANT_ADQUIRIVEL")
    private Double quantidade;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "ADQUIRIVEL",
            referencedColumnName = "ID_ADQUIRIVEL",
            foreignKey = @ForeignKey(name = "TB_SOLICITACAO_FK_ADQUIRIVEL")
    )
    private Adquirivel adquirivel;

    
}
