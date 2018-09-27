package br.com.erucae.segurancatrabalho.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import br.com.erucae.segurancatrabalho.domain.enumeration.TrainingApplicable;

/**
 * TrainingItem class
 * Table 'tb_training_item'
 * 
 * @author rafaelbenevides
 * @company Queiroz Galvão
 */
@ApiModel(description = "TrainingItem class Table 'tb_training_item' @author rafaelbenevides @company Queiroz Galvão")
@Entity
@Table(name = "tb_training_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TrainingItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "training_applicable")
    private TrainingApplicable trainingApplicable;

    @Column(name = "certificate_validity")
    private Integer certificateValidity;

    @Column(name = "hours_of_training")
    private Integer hoursOfTraining;

    @ManyToOne
    @JsonIgnoreProperties("items")
    private Training training;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TrainingType trainingType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public TrainingItem date(LocalDate date) {
        this.date = date;
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TrainingApplicable getTrainingApplicable() {
        return trainingApplicable;
    }

    public TrainingItem trainingApplicable(TrainingApplicable trainingApplicable) {
        this.trainingApplicable = trainingApplicable;
        return this;
    }

    public void setTrainingApplicable(TrainingApplicable trainingApplicable) {
        this.trainingApplicable = trainingApplicable;
    }

    public Integer getCertificateValidity() {
        return certificateValidity;
    }

    public TrainingItem certificateValidity(Integer certificateValidity) {
        this.certificateValidity = certificateValidity;
        return this;
    }

    public void setCertificateValidity(Integer certificateValidity) {
        this.certificateValidity = certificateValidity;
    }

    public Integer getHoursOfTraining() {
        return hoursOfTraining;
    }

    public TrainingItem hoursOfTraining(Integer hoursOfTraining) {
        this.hoursOfTraining = hoursOfTraining;
        return this;
    }

    public void setHoursOfTraining(Integer hoursOfTraining) {
        this.hoursOfTraining = hoursOfTraining;
    }

    public Training getTraining() {
        return training;
    }

    public TrainingItem training(Training training) {
        this.training = training;
        return this;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public TrainingItem trainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
        return this;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TrainingItem trainingItem = (TrainingItem) o;
        if (trainingItem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trainingItem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrainingItem{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", trainingApplicable='" + getTrainingApplicable() + "'" +
            ", certificateValidity=" + getCertificateValidity() +
            ", hoursOfTraining=" + getHoursOfTraining() +
            "}";
    }
}
