package br.com.fiap.smartsuplify.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import br.com.fiap.smartsuplify.model.pessoa.PessoaFisica;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_COTACAO")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_COTACAO")
    @SequenceGenerator(name = "SQ_COTACAO", sequenceName = "SQ_COTACAO", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_COTACAO")
    private Long id;

    @Column(name = "DATA_COTACAO")
    @NotBlank
    private LocalDate data;

    @Column(name = "STATUS_COTACAO")
    @NotBlank
    private String status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinTable(
            name = "TB_COTACAO_SOLICITACAO",
            joinColumns = {
                    @JoinColumn(
                            name = "COTACAO",
                            referencedColumnName = "ID_COTACAO",
                            foreignKey = @ForeignKey(name = "FK_TB_COTACAO_COTACAO")
                    )
            },
            inverseJoinColumns = {

                    @JoinColumn(
                            name = "SOLICITACAO",
                            referencedColumnName = "ID_SOLICITACAO",
                            foreignKey = @ForeignKey(name = "FK_TB_COTACAO_SOLICITACAO")
                    )
            }
    )
    private Set<Solicitacao> solicitacaos = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(
            name = "FUNCIONARIO",
            referencedColumnName = "ID_FUNCIONARIO",
            foreignKey = @ForeignKey(name = "TB_COTACAO_FK_FUNCIONARIO")
    )
    private PessoaFisica funcionario;
    
    public Cotacao addSolicitacao(Solicitacao solicitacao){
        solicitacaos.add(solicitacao);
        return this;
    }

    public Cotacao removeSolicitacao(Solicitacao solicitacao){
        solicitacaos.remove(solicitacao);
        return this;
    }

    public Cotacao withId(Long id){
        this.id = id;
        return this;
    }

    public Cotacao withData(LocalDate data){
        this.data = data;
        return this;
    }

    public Cotacao withStatus(String status){
        this.status = status;
        return this;
    }

    public Cotacao withFuncionario(PessoaFisica funcionario){
        this.funcionario = funcionario;
        return this;
    }
}
