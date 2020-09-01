package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Tags.
 */
@Entity
@Table(name = "tags")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Tags extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tag_name")
    private String tagName;

    @Column(name = "tag_value")
    private String tagValue;

    @Column(name = "health_condition_tag")
    private String healthConditionTag;

    @Column(name = "covid_positive_tag")
    private String covidPositiveTag;

    @Column(name = "follow_up_tag")
    private String followUpTag;

    @Column(name = "treatment_tag")
    private String treatmentTag;

    @Column(name = "metadata")
    private String metadata;

    @ManyToOne
    @JsonIgnoreProperties(value = "tags", allowSetters = true)
    private Ticket ticket;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public Tags tagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagValue() {
        return tagValue;
    }

    public Tags tagValue(String tagValue) {
        this.tagValue = tagValue;
        return this;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public String getHealthConditionTag() {
        return healthConditionTag;
    }

    public Tags healthConditionTag(String healthConditionTag) {
        this.healthConditionTag = healthConditionTag;
        return this;
    }

    public void setHealthConditionTag(String healthConditionTag) {
        this.healthConditionTag = healthConditionTag;
    }

    public String getCovidPositiveTag() {
        return covidPositiveTag;
    }

    public Tags covidPositiveTag(String covidPositiveTag) {
        this.covidPositiveTag = covidPositiveTag;
        return this;
    }

    public void setCovidPositiveTag(String covidPositiveTag) {
        this.covidPositiveTag = covidPositiveTag;
    }

    public String getFollowUpTag() {
        return followUpTag;
    }

    public Tags followUpTag(String followUpTag) {
        this.followUpTag = followUpTag;
        return this;
    }

    public void setFollowUpTag(String followUpTag) {
        this.followUpTag = followUpTag;
    }

    public String getTreatmentTag() {
        return treatmentTag;
    }

    public Tags treatmentTag(String treatmentTag) {
        this.treatmentTag = treatmentTag;
        return this;
    }

    public void setTreatmentTag(String treatmentTag) {
        this.treatmentTag = treatmentTag;
    }

    public String getMetadata() {
        return metadata;
    }

    public Tags metadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Tags ticket(Ticket ticket) {
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
        if (!(o instanceof Tags)) {
            return false;
        }
        return id != null && id.equals(((Tags) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Tags{" +
            "id=" + getId() +
            ", tagName='" + getTagName() + "'" +
            ", tagValue='" + getTagValue() + "'" +
            ", healthConditionTag='" + getHealthConditionTag() + "'" +
            ", covidPositiveTag='" + getCovidPositiveTag() + "'" +
            ", followUpTag='" + getFollowUpTag() + "'" +
            ", treatmentTag='" + getTreatmentTag() + "'" +
            ", metadata='" + getMetadata() + "'" +
            "}";
    }
}
