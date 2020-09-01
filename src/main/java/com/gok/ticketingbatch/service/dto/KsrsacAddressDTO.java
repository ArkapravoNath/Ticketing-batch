package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.KsrsacAddress} entity.
 */
public class KsrsacAddressDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String districtCode;

    private String districtName;

    private String hobliCode;

    private String surveynum;

    private String talukCode;

    private String talukName;

    private String type;

    private String villageCode;

    private String villageName;

    private String hobliName;

    private String message;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getHobliCode() {
        return hobliCode;
    }

    public void setHobliCode(String hobliCode) {
        this.hobliCode = hobliCode;
    }

    public String getSurveynum() {
        return surveynum;
    }

    public void setSurveynum(String surveynum) {
        this.surveynum = surveynum;
    }

    public String getTalukCode() {
        return talukCode;
    }

    public void setTalukCode(String talukCode) {
        this.talukCode = talukCode;
    }

    public String getTalukName() {
        return talukName;
    }

    public void setTalukName(String talukName) {
        this.talukName = talukName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getHobliName() {
        return hobliName;
    }

    public void setHobliName(String hobliName) {
        this.hobliName = hobliName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KsrsacAddressDTO)) {
            return false;
        }

        return id != null && id.equals(((KsrsacAddressDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KsrsacAddressDTO{" +
            "id=" + getId() +
            ", districtCode='" + getDistrictCode() + "'" +
            ", districtName='" + getDistrictName() + "'" +
            ", hobliCode='" + getHobliCode() + "'" +
            ", surveynum='" + getSurveynum() + "'" +
            ", talukCode='" + getTalukCode() + "'" +
            ", talukName='" + getTalukName() + "'" +
            ", type='" + getType() + "'" +
            ", villageCode='" + getVillageCode() + "'" +
            ", villageName='" + getVillageName() + "'" +
            ", hobliName='" + getHobliName() + "'" +
            ", message='" + getMessage() + "'" +
            "}";
    }
}
