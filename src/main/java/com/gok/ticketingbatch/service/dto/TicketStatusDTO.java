package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.TicketStatus} entity.
 */
public class TicketStatusDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String status;

    private String comments;

    private Long assigneeIdRef;


    private Long ticketId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getAssigneeIdRef() {
        return assigneeIdRef;
    }

    public void setAssigneeIdRef(Long assigneeIdRef) {
        this.assigneeIdRef = assigneeIdRef;
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
        if (!(o instanceof TicketStatusDTO)) {
            return false;
        }

        return id != null && id.equals(((TicketStatusDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TicketStatusDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", comments='" + getComments() + "'" +
            ", assigneeIdRef=" + getAssigneeIdRef() +
            ", ticketId=" + getTicketId() +
            "}";
    }
}
