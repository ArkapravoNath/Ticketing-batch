package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A KsrsacAddress.
 */
@Entity
@Table(name = "ksrsac_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class KsrsacAddress extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "hobli_code")
    private String hobliCode;

    @Column(name = "surveynum")
    private String surveynum;

    @Column(name = "taluk_code")
    private String talukCode;

    @Column(name = "taluk_name")
    private String talukName;

    @Column(name = "type")
    private String type;

    @Column(name = "village_code")
    private String villageCode;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "hobli_name")
    private String hobliName;

    @Column(name = "message")
    private String message;

    @OneToOne(mappedBy = "ksrsacAddress")
    @JsonIgnore
    private Address address;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public KsrsacAddress districtCode(String districtCode) {
        this.districtCode = districtCode;
        return this;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public KsrsacAddress districtName(String districtName) {
        this.districtName = districtName;
        return this;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getHobliCode() {
        return hobliCode;
    }

    public KsrsacAddress hobliCode(String hobliCode) {
        this.hobliCode = hobliCode;
        return this;
    }

    public void setHobliCode(String hobliCode) {
        this.hobliCode = hobliCode;
    }

    public String getSurveynum() {
        return surveynum;
    }

    public KsrsacAddress surveynum(String surveynum) {
        this.surveynum = surveynum;
        return this;
    }

    public void setSurveynum(String surveynum) {
        this.surveynum = surveynum;
    }

    public String getTalukCode() {
        return talukCode;
    }

    public KsrsacAddress talukCode(String talukCode) {
        this.talukCode = talukCode;
        return this;
    }

    public void setTalukCode(String talukCode) {
        this.talukCode = talukCode;
    }

    public String getTalukName() {
        return talukName;
    }

    public KsrsacAddress talukName(String talukName) {
        this.talukName = talukName;
        return this;
    }

    public void setTalukName(String talukName) {
        this.talukName = talukName;
    }

    public String getType() {
        return type;
    }

    public KsrsacAddress type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public KsrsacAddress villageCode(String villageCode) {
        this.villageCode = villageCode;
        return this;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillageName() {
        return villageName;
    }

    public KsrsacAddress villageName(String villageName) {
        this.villageName = villageName;
        return this;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getHobliName() {
        return hobliName;
    }

    public KsrsacAddress hobliName(String hobliName) {
        this.hobliName = hobliName;
        return this;
    }

    public void setHobliName(String hobliName) {
        this.hobliName = hobliName;
    }

    public String getMessage() {
        return message;
    }

    public KsrsacAddress message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Address getAddress() {
        return address;
    }

    public KsrsacAddress address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof KsrsacAddress)) {
            return false;
        }
        return id != null && id.equals(((KsrsacAddress) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "KsrsacAddress{" +
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
