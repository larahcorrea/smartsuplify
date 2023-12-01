package br.com.fiap.smartsuplify.model.adquirivel;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Table(name = "TB_ADQUIRIVEL")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TP_ADQUIRIVEL")
public class Adquirivel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ADQUIRIVEL")
    @SequenceGenerator(name = "SQ_ADQUIRIVEL", sequenceName = "SQ_ADQUIRIVEL", allocationSize = 1, initialValue = 1)
    @Column(name = "ID_ADQUIRIVEL")
    private Long id;

    @Column(name = "NM_ADQUIRIVEL")
    @NotBlank
    private String nome;
}