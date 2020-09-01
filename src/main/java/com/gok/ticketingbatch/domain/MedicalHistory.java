package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MedicalHistory.
 */
@Entity
@Table(name = "medical_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MedicalHistory extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "metadata")
    private String metadata;

    @Column(name = "cancer")
    private String cancer;

    @Column(name = "tb_or_hiv")
    private String tbOrHiv;

    @Column(name = "renal_problem")
    private String renalProblem;

    @ManyToOne
    @JsonIgnoreProperties(value = "medicalHistories", allowSetters = true)
    private Patient patient;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetadata() {
        return metadata;
    }

    public MedicalHistory metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getCancer() {
        return cancer;
    }

    public MedicalHistory cancer(String cancer) {
        this.cancer = cancer;
        return this;
    }

    public void setCancer(String cancer) {
        this.cancer = cancer;
    }

    public String getTbOrHiv() {
        return tbOrHiv;
    }

    public MedicalHistory tbOrHiv(String tbOrHiv) {
        this.tbOrHiv = tbOrHiv;
        return this;
    }

    public void setTbOrHiv(String tbOrHiv) {
        this.tbOrHiv = tbOrHiv;
    }

    public String getRenalProblem() {
        return renalProblem;
    }

    public MedicalHistory renalProblem(String renalProblem) {
        this.renalProblem = renalProblem;
        return this;
    }

    public void setRenalProblem(String renalProblem) {
        this.renalProblem = renalProblem;
    }

    public Patient getPatient() {
        return patient;
    }

    public MedicalHistory patient(Patient patient) {
        this.patient = patient;
        return this;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MedicalHistory)) {
            return false;
        }
        return id != null && id.equals(((MedicalHistory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MedicalHistory{" +
            "id=" + getId() +
            ", metadata='" + getMetadata() + "'" +
            ", cancer='" + getCancer() + "'" +
            ", tbOrHiv='" + getTbOrHiv() + "'" +
            ", renalProblem='" + getRenalProblem() + "'" +
            "}";
    }
}
