package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.Ticket} entity.
 */
public class TicketDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String ticketName;

    private String status;

    private String state;

    private String channel;

    private String category;

    private String subcategory;

    private String contactDetails;

    private String mobileNumber;

    private Long patinetIdRef;

    private Long ticketIdRef;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getPatinetIdRef() {
        return patinetIdRef;
    }

    public void setPatinetIdRef(Long patinetIdRef) {
        this.patinetIdRef = patinetIdRef;
    }

    public Long getTicketIdRef() {
        return ticketIdRef;
    }

    public void setTicketIdRef(Long ticketIdRef) {
        this.ticketIdRef = ticketIdRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TicketDTO)) {
            return false;
        }

        return id != null && id.equals(((TicketDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TicketDTO{" +
            "id=" + getId() +
            ", ticketName='" + getTicketName() + "'" +
            ", status='" + getStatus() + "'" +
            ", state='" + getState() + "'" +
            ", channel='" + getChannel() + "'" +
            ", category='" + getCategory() + "'" +
            ", subcategory='" + getSubcategory() + "'" +
            ", contactDetails='" + getContactDetails() + "'" +
            ", mobileNumber='" + getMobileNumber() + "'" +
            ", patinetIdRef=" + getPatinetIdRef() +
            ", ticketIdRef=" + getTicketIdRef() +
            "}";
    }
}
