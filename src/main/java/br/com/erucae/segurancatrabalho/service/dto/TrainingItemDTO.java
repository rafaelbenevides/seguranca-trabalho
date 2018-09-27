package br.com.erucae.segurancatrabalho.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import br.com.erucae.segurancatrabalho.domain.enumeration.TrainingApplicable;

/**
 * A DTO for the TrainingItem entity.
 */
public class TrainingItemDTO implements Serializable {

    private Long id;

    private LocalDate date;

    private TrainingApplicable trainingApplicable;

    private Integer certificateValidity;

    private Integer hoursOfTraining;

    private Long trainingId;

    private Long trainingTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TrainingApplicable getTrainingApplicable() {
        return trainingApplicable;
    }

    public void setTrainingApplicable(TrainingApplicable trainingApplicable) {
        this.trainingApplicable = trainingApplicable;
    }

    public Integer getCertificateValidity() {
        return certificateValidity;
    }

    public void setCertificateValidity(Integer certificateValidity) {
        this.certificateValidity = certificateValidity;
    }

    public Integer getHoursOfTraining() {
        return hoursOfTraining;
    }

    public void setHoursOfTraining(Integer hoursOfTraining) {
        this.hoursOfTraining = hoursOfTraining;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public Long getTrainingTypeId() {
        return trainingTypeId;
    }

    public void setTrainingTypeId(Long trainingTypeId) {
        this.trainingTypeId = trainingTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TrainingItemDTO trainingItemDTO = (TrainingItemDTO) o;
        if (trainingItemDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trainingItemDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TrainingItemDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", trainingApplicable='" + getTrainingApplicable() + "'" +
            ", certificateValidity=" + getCertificateValidity() +
            ", hoursOfTraining=" + getHoursOfTraining() +
            ", training=" + getTrainingId() +
            ", trainingType=" + getTrainingTypeId() +
            "}";
    }
}
