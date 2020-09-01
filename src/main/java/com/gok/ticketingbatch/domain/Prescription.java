package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Prescription.
 */
@Entity
@Table(name = "prescription")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Prescription extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "otc_meds_prescription")
    private String otcMedsPrescription;

    @Column(name = "any_other_prescription")
    private String anyOtherPrescription;

    @Column(name = "advisory_issues")
    private String advisoryIssues;

    @Column(name = "method_of_capture")
    private String methodOfCapture;

    @Column(name = "referred_clinic")
    private String referredClinic;

    @Column(name = "referred_clinic_id")
    private String referredClinicId;

    @Lob
    @Column(name = "prescription_image")
    private byte[] prescriptionImage;

    @Column(name = "prescription_image_content_type")
    private String prescriptionImageContentType;

    @Column(name = "prescription_image_content_type")
    private String prescriptionImageContentType;

    @Column(name = "prescribed_by_id")
    private String prescribedById;

    @Column(name = "facility_provider_id_ref")
    private Long facilityProviderIdRef;

    @ManyToOne
    @JsonIgnoreProperties(value = "prescriptions", allowSetters = true)
    private Ticket ticket;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOtcMedsPrescription() {
        return otcMedsPrescription;
    }

    public Prescription otcMedsPrescription(String otcMedsPrescription) {
        this.otcMedsPrescription = otcMedsPrescription;
        return this;
    }

    public void setOtcMedsPrescription(String otcMedsPrescription) {
        this.otcMedsPrescription = otcMedsPrescription;
    }

    public String getAnyOtherPrescription() {
        return anyOtherPrescription;
    }

    public Prescription anyOtherPrescription(String anyOtherPrescription) {
        this.anyOtherPrescription = anyOtherPrescription;
        return this;
    }

    public void setAnyOtherPrescription(String anyOtherPrescription) {
        this.anyOtherPrescription = anyOtherPrescription;
    }

    public String getAdvisoryIssues() {
        return advisoryIssues;
    }

    public Prescription advisoryIssues(String advisoryIssues) {
        this.advisoryIssues = advisoryIssues;
        return this;
    }

    public void setAdvisoryIssues(String advisoryIssues) {
        this.advisoryIssues = advisoryIssues;
    }

    public String getMethodOfCapture() {
        return methodOfCapture;
    }

    public Prescription methodOfCapture(String methodOfCapture) {
        this.methodOfCapture = methodOfCapture;
        return this;
    }

    public void setMethodOfCapture(String methodOfCapture) {
        this.methodOfCapture = methodOfCapture;
    }

    public String getReferredClinic() {
        return referredClinic;
    }

    public Prescription referredClinic(String referredClinic) {
        this.referredClinic = referredClinic;
        return this;
    }

    public void setReferredClinic(String referredClinic) {
        this.referredClinic = referredClinic;
    }

    public String getReferredClinicId() {
        return referredClinicId;
    }

    public Prescription referredClinicId(String referredClinicId) {
        this.referredClinicId = referredClinicId;
        return this;
    }

    public void setReferredClinicId(String referredClinicId) {
        this.referredClinicId = referredClinicId;
    }

    public byte[] getPrescriptionImage() {
        return prescriptionImage;
    }

    public Prescription prescriptionImage(byte[] prescriptionImage) {
        this.prescriptionImage = prescriptionImage;
        return this;
    }

    public void setPrescriptionImage(byte[] prescriptionImage) {
        this.prescriptionImage = prescriptionImage;
    }

    public String getPrescriptionImageContentType() {
        return prescriptionImageContentType;
    }

    public Prescription prescriptionImageContentType(String prescriptionImageContentType) {
        this.prescriptionImageContentType = prescriptionImageContentType;
        return this;
    }

    public void setPrescriptionImageContentType(String prescriptionImageContentType) {
        this.prescriptionImageContentType = prescriptionImageContentType;
    }

    public String getPrescriptionImageContentType() {
        return prescriptionImageContentType;
    }

    public Prescription prescriptionImageContentType(String prescriptionImageContentType) {
        this.prescriptionImageContentType = prescriptionImageContentType;
        return this;
    }

    public void setPrescriptionImageContentType(String prescriptionImageContentType) {
        this.prescriptionImageContentType = prescriptionImageContentType;
    }

    public String getPrescribedById() {
        return prescribedById;
    }

    public Prescription prescribedById(String prescribedById) {
        this.prescribedById = prescribedById;
        return this;
    }

    public void setPrescribedById(String prescribedById) {
        this.prescribedById = prescribedById;
    }

    public Long getFacilityProviderIdRef() {
        return facilityProviderIdRef;
    }

    public Prescription facilityProviderIdRef(Long facilityProviderIdRef) {
        this.facilityProviderIdRef = facilityProviderIdRef;
        return this;
    }

    public void setFacilityProviderIdRef(Long facilityProviderIdRef) {
        this.facilityProviderIdRef = facilityProviderIdRef;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Prescription ticket(Ticket ticket) {
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
        if (!(o instanceof Prescription)) {
            return false;
        }
        return id != null && id.equals(((Prescription) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Prescription{" +
            "id=" + getId() +
            ", otcMedsPrescription='" + getOtcMedsPrescription() + "'" +
            ", anyOtherPrescription='" + getAnyOtherPrescription() + "'" +
            ", advisoryIssues='" + getAdvisoryIssues() + "'" +
            ", methodOfCapture='" + getMethodOfCapture() + "'" +
            ", referredClinic='" + getReferredClinic() + "'" +
            ", referredClinicId='" + getReferredClinicId() + "'" +
            ", prescriptionImage='" + getPrescriptionImage() + "'" +
            ", prescriptionImageContentType='" + getPrescriptionImageContentType() + "'" +
            ", prescriptionImageContentType='" + getPrescriptionImageContentType() + "'" +
            ", prescribedById='" + getPrescribedById() + "'" +
            ", facilityProviderIdRef=" + getFacilityProviderIdRef() +
            "}";
    }
}
