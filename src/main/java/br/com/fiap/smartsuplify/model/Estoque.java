package br.com.fiap.smartsuplify.model;

import br.com.fiap.smartsuplify.model.adquirivel.Adquirivel;
import br.com.fiap.smartsuplify.model.pessoa.PessoaJuridica;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ESTOQUE")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ESTOQUE")
    @SequenceGenerator(name = "SQ_ESTOQUE", sequenceName = "SQ_ESTOQUE", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_ESTOQUE")
    private Long id;

    @Column(name = "QUANT_MIN")
    private int quantidadeMin;

    @Column(name = "QUANT_MAX")
    private int quantidadeMax;

    @Column(name = "QUANT_ATUAL")
    private int quantidadeAtual;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "ADQUIRIVEL",
            referencedColumnName = "ID_ADQUIRIVEL",
            foreignKey = @ForeignKey(name = "TB_ESTOQUE_FK_ADQUIRIVEL")
    )
    private Adquirivel adquirivel;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "EMPRESA",
            referencedColumnName = "ID_PESSOA",
            foreignKey = @ForeignKey(name = "TB_ESTOQUE_FK_EMPRESA")
    )
    private PessoaJuridica empresa;
    
}
