package com.gok.ticketingbatch.service.dto;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.gok.ticketingbatch.domain.Prescription} entity.
 */
public class PrescriptionDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String otcMedsPrescription;

    private String anyOtherPrescription;

    private String advisoryIssues;

    private String methodOfCapture;

    private String referredClinic;

    private String referredClinicId;

    @Lob
    private byte[] prescriptionImage;

    private String prescriptionImageContentType;
    private String prescriptionImageContentType;

    private String prescribedById;

    private Long facilityProviderIdRef;


    private Long ticketId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtcMedsPrescription() {
        return otcMedsPrescription;
    }

    public void setOtcMedsPrescription(String otcMedsPrescription) {
        this.otcMedsPrescription = otcMedsPrescription;
    }

    public String getAnyOtherPrescription() {
        return anyOtherPrescription;
    }

    public void setAnyOtherPrescription(String anyOtherPrescription) {
        this.anyOtherPrescription = anyOtherPrescription;
    }

    public String getAdvisoryIssues() {
        return advisoryIssues;
    }

    public void setAdvisoryIssues(String advisoryIssues) {
        this.advisoryIssues = advisoryIssues;
    }

    public String getMethodOfCapture() {
        return methodOfCapture;
    }

    public void setMethodOfCapture(String methodOfCapture) {
        this.methodOfCapture = methodOfCapture;
    }

    public String getReferredClinic() {
        return referredClinic;
    }

    public void setReferredClinic(String referredClinic) {
        this.referredClinic = referredClinic;
    }

    public String getReferredClinicId() {
        return referredClinicId;
    }

    public void setReferredClinicId(String referredClinicId) {
        this.referredClinicId = referredClinicId;
    }

    public byte[] getPrescriptionImage() {
        return prescriptionImage;
    }

    public void setPrescriptionImage(byte[] prescriptionImage) {
        this.prescriptionImage = prescriptionImage;
    }

    public String getPrescriptionImageContentType() {
        return prescriptionImageContentType;
    }

    public void setPrescriptionImageContentType(String prescriptionImageContentType) {
        this.prescriptionImageContentType = prescriptionImageContentType;
    }

    public String getPrescriptionImageContentType() {
        return prescriptionImageContentType;
    }

    public void setPrescriptionImageContentType(String prescriptionImageContentType) {
        this.prescriptionImageContentType = prescriptionImageContentType;
    }

    public String getPrescribedById() {
        return prescribedById;
    }

    public void setPrescribedById(String prescribedById) {
        this.prescribedById = prescribedById;
    }

    public Long getFacilityProviderIdRef() {
        return facilityProviderIdRef;
    }

    public void setFacilityProviderIdRef(Long facilityProviderIdRef) {
        this.facilityProviderIdRef = facilityProviderIdRef;
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
        if (!(o instanceof PrescriptionDTO)) {
            return false;
        }

        return id != null && id.equals(((PrescriptionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PrescriptionDTO{" +
            "id=" + getId() +
            ", otcMedsPrescription='" + getOtcMedsPrescription() + "'" +
            ", anyOtherPrescription='" + getAnyOtherPrescription() + "'" +
            ", advisoryIssues='" + getAdvisoryIssues() + "'" +
            ", methodOfCapture='" + getMethodOfCapture() + "'" +
            ", referredClinic='" + getReferredClinic() + "'" +
            ", referredClinicId='" + getReferredClinicId() + "'" +
            ", prescriptionImage='" + getPrescriptionImage() + "'" +
            ", prescriptionImageContentType='" + getPrescriptionImageContentType() + "'" +
            ", prescribedById='" + getPrescribedById() + "'" +
            ", facilityProviderIdRef=" + getFacilityProviderIdRef() +
            ", ticketId=" + getTicketId() +
            "}";
    }
}
