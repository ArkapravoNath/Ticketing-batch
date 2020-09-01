package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Diagnosis.
 */
@Entity
@Table(name = "diagnosis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Diagnosis extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "test_result")
    private Boolean testResult;

    @Column(name = "detailed_results")
    private String detailedResults;

    @ManyToOne
    @JsonIgnoreProperties(value = "diagnoses", allowSetters = true)
    private Ticket ticket;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isTestResult() {
        return testResult;
    }

    public Diagnosis testResult(Boolean testResult) {
        this.testResult = testResult;
        return this;
    }

    public void setTestResult(Boolean testResult) {
        this.testResult = testResult;
    }

    public String getDetailedResults() {
        return detailedResults;
    }

    public Diagnosis detailedResults(String detailedResults) {
        this.detailedResults = detailedResults;
        return this;
    }

    public void setDetailedResults(String detailedResults) {
        this.detailedResults = detailedResults;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Diagnosis ticket(Ticket ticket) {
        this.ticket = ticket;
        return this;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Diagnosis)) {
            return false;
        }
        return id != null && id.equals(((Diagnosis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Diagnosis{" +
            "id=" + getId() +
            ", testResult='" + isTestResult() + "'" +
            ", detailedResults='" + getDetailedResults() + "'" +
            "}";
    }
}
