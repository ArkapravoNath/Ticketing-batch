package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.Diagnosis} entity.
 */
public class DiagnosisDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private Boolean testResult;

    private String detailedResults;


    private Long ticketId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isTestResult() {
        return testResult;
    }

    public void setTestResult(Boolean testResult) {
        this.testResult = testResult;
    }

    public String getDetailedResults() {
        return detailedResults;
    }

    public void setDetailedResults(String detailedResults) {
        this.detailedResults = detailedResults;
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
        if (!(o instanceof DiagnosisDTO)) {
            return false;
        }

        return id != null && id.equals(((DiagnosisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DiagnosisDTO{" +
            "id=" + getId() +
            ", testResult='" + isTestResult() + "'" +
            ", detailedResults='" + getDetailedResults() + "'" +
            ", ticketId=" + getTicketId() +
            "}";
    }
}
