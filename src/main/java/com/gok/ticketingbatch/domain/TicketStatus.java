package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TicketStatus.
 */
@Entity
@Table(name = "ticket_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TicketStatus extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "comments")
    private String comments;

    @Column(name = "assignee_id_ref")
    private Long assigneeIdRef;

    @ManyToOne
    @JsonIgnoreProperties(value = "ticketStatuses", allowSetters = true)
    private Ticket ticket;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public TicketStatus status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public TicketStatus comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getAssigneeIdRef() {
        return assigneeIdRef;
    }

    public TicketStatus assigneeIdRef(Long assigneeIdRef) {
        this.assigneeIdRef = assigneeIdRef;
        return this;
    }

    public void setAssigneeIdRef(Long assigneeIdRef) {
        this.assigneeIdRef = assigneeIdRef;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public TicketStatus ticket(Ticket ticket) {
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
        if (!(o instanceof TicketStatus)) {
            return false;
        }
        return id != null && id.equals(((TicketStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TicketStatus{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", comments='" + getComments() + "'" +
            ", assigneeIdRef=" + getAssigneeIdRef() +
            "}";
    }
}
