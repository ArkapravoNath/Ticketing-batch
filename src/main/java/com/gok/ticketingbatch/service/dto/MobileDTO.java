package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.Mobile} entity.
 */
public class MobileDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String number;

    private String metadata;


    private Long personId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MobileDTO)) {
            return false;
        }

        return id != null && id.equals(((MobileDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MobileDTO{" +
            "id=" + getId() +
            ", number='" + getNumber() + "'" +
            ", metadata='" + getMetadata() + "'" +
            ", personId=" + getPersonId() +
            "}";
    }
}
