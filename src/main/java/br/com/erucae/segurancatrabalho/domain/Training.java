package br.com.erucae.segurancatrabalho.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Training class
 * Table 'tb_training'
 *
 * @author rafaelbenevides
 * @company Queiroz Galvão
 */
@ApiModel(description = "Training class Table 'tb_training' @author rafaelbenevides @company Queiroz Galvão")
@Entity
@Table(name = "tb_training")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Training implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TrainingItem> items = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("")
    private Plant plant;

    @ManyToOne
    @JsonIgnoreProperties("")
    private PlantType plantType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private Employee employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<TrainingItem> getItems() {
        return items;
    }

    public Training items(Set<TrainingItem> trainingItems) {
        this.items = trainingItems;
        return this;
    }

    public Training addItems(TrainingItem trainingItem) {
        this.items.add(trainingItem);
        trainingItem.setTraining(this);
        return this;
    }

    public Training removeItems(TrainingItem trainingItem) {
        this.items.remove(trainingItem);
        trainingItem.setTraining(null);
        return this;
    }

    public void setItems(Set<TrainingItem> trainingItems) {
        this.items = trainingItems;
    }

    public Plant getPlant() {
        return plant;
    }

    public Training plant(Plant plant) {
        this.plant = plant;
        return this;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public PlantType getPlantType() {
        return plantType;
    }

    public Training plantType(PlantType plantType) {
        this.plantType = plantType;
        return this;
    }

    public void setPlantType(PlantType plantType) {
        this.plantType = plantType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Training employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        Training training = (Training) o;
        if (training.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), training.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Training{" +
            "id=" + getId() +
            "}";
    }
}
