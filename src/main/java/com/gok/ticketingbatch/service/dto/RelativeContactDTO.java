package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.RelativeContact} entity.
 */
public class RelativeContactDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String type;

    private String relationshipType;


    private Long personId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
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
        if (!(o instanceof RelativeContactDTO)) {
            return false;
        }

        return id != null && id.equals(((RelativeContactDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelativeContactDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", relationshipType='" + getRelationshipType() + "'" +
            ", personId=" + getPersonId() +
            "}";
    }
}
