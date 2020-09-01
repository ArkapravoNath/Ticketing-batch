package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.Tags} entity.
 */
public class TagsDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String tagName;

    private String tagValue;

    private String healthConditionTag;

    private String covidPositiveTag;

    private String followUpTag;

    private String treatmentTag;

    private String metadata;


    private Long ticketId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagValue() {
        return tagValue;
    }

    public void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    public String getHealthConditionTag() {
        return healthConditionTag;
    }

    public void setHealthConditionTag(String healthConditionTag) {
        this.healthConditionTag = healthConditionTag;
    }

    public String getCovidPositiveTag() {
        return covidPositiveTag;
    }

    public void setCovidPositiveTag(String covidPositiveTag) {
        this.covidPositiveTag = covidPositiveTag;
    }

    public String getFollowUpTag() {
        return followUpTag;
    }

    public void setFollowUpTag(String followUpTag) {
        this.followUpTag = followUpTag;
    }

    public String getTreatmentTag() {
        return treatmentTag;
    }

    public void setTreatmentTag(String treatmentTag) {
        this.treatmentTag = treatmentTag;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TagsDTO)) {
            return false;
        }

        return id != null && id.equals(((TagsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TagsDTO{" +
            "id=" + getId() +
            ", tagName='" + getTagName() + "'" +
            ", tagValue='" + getTagValue() + "'" +
            ", healthConditionTag='" + getHealthConditionTag() + "'" +
            ", covidPositiveTag='" + getCovidPositiveTag() + "'" +
            ", followUpTag='" + getFollowUpTag() + "'" +
            ", treatmentTag='" + getTreatmentTag() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", ticketId=" + getTicketId() +
            "}";
    }
}
