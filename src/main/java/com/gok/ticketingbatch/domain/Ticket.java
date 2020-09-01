package com.gok.ticketingbatch.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Ticket.
 */
@Entity
@Table(name = "ticket")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Ticket extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ticket_name")
    private String ticketName;

    @Column(name = "status")
    private String status;

    @Column(name = "state")
    private String state;

    @Column(name = "channel")
    private String channel;

    @Column(name = "category")
    private String category;

    @Column(name = "subcategory")
    private String subcategory;

    @Column(name = "contact_details")
    private String contactDetails;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "patinet_id_ref")
    private Long patinetIdRef;

    @Column(name = "ticket_id_ref")
    private Long ticketIdRef;

    @OneToMany(mappedBy = "ticket")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TicketAction> ticketActions = new HashSet<>();

    @OneToMany(mappedBy = "ticket")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Prescription> prescriptions = new HashSet<>();

    @OneToMany(mappedBy = "ticket")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Diagnosis> diagnoses = new HashSet<>();

    @OneToMany(mappedBy = "ticket")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Tags> tags = new HashSet<>();

    @OneToMany(mappedBy = "ticket")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<TicketStatus> ticketStatuses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicketName() {
        return ticketName;
    }

    public Ticket ticketName(String ticketName) {
        this.ticketName = ticketName;
        return this;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getStatus() {
        return status;
    }

    public Ticket status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public Ticket state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getChannel() {
        return channel;
    }

    public Ticket channel(String channel) {
        this.channel = channel;
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCategory() {
        return category;
    }

    public Ticket category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public Ticket subcategory(String subcategory) {
        this.subcategory = subcategory;
        return this;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public Ticket contactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
        return this;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public Ticket mobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        return this;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Long getPatinetIdRef() {
        return patinetIdRef;
    }

    public Ticket patinetIdRef(Long patinetIdRef) {
        this.patinetIdRef = patinetIdRef;
        return this;
    }

    public void setPatinetIdRef(Long patinetIdRef) {
        this.patinetIdRef = patinetIdRef;
    }

    public Long getTicketIdRef() {
        return ticketIdRef;
    }

    public Ticket ticketIdRef(Long ticketIdRef) {
        this.ticketIdRef = ticketIdRef;
        return this;
    }

    public void setTicketIdRef(Long ticketIdRef) {
        this.ticketIdRef = ticketIdRef;
    }

    public Set<TicketAction> getTicketActions() {
        return ticketActions;
    }

    public Ticket ticketActions(Set<TicketAction> ticketActions) {
        this.ticketActions = ticketActions;
        return this;
    }

    public Ticket addTicketAction(TicketAction ticketAction) {
        this.ticketActions.add(ticketAction);
        ticketAction.setTicket(this);
        return this;
    }

    public Ticket removeTicketAction(TicketAction ticketAction) {
        this.ticketActions.remove(ticketAction);
        ticketAction.setTicket(null);
        return this;
    }

    public void setTicketActions(Set<TicketAction> ticketActions) {
        this.ticketActions = ticketActions;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public Ticket prescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
        return this;
    }

    public Ticket addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
        prescription.setTicket(this);
        return this;
    }

    public Ticket removePrescription(Prescription prescription) {
        this.prescriptions.remove(prescription);
        prescription.setTicket(null);
        return this;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Set<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public Ticket diagnoses(Set<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
        return this;
    }

    public Ticket addDiagnosis(Diagnosis diagnosis) {
        this.diagnoses.add(diagnosis);
        diagnosis.setTicket(this);
        return this;
    }

    public Ticket removeDiagnosis(Diagnosis diagnosis) {
        this.diagnoses.remove(diagnosis);
        diagnosis.setTicket(null);
        return this;
    }

    public void setDiagnoses(Set<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<Tags> getTags() {
        return tags;
    }

    public Ticket tags(Set<Tags> tags) {
        this.tags = tags;
        return this;
    }

    public Ticket addTags(Tags tags) {
        this.tags.add(tags);
        tags.setTicket(this);
        return this;
    }

    public Ticket removeTags(Tags tags) {
        this.tags.remove(tags);
        tags.setTicket(null);
        return this;
    }

    public void setTags(Set<Tags> tags) {
        this.tags = tags;
    }

    public Set<TicketStatus> getTicketStatuses() {
        return ticketStatuses;
    }

    public Ticket ticketStatuses(Set<TicketStatus> ticketStatuses) {
        this.ticketStatuses = ticketStatuses;
        return this;
    }

    public Ticket addTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatuses.add(ticketStatus);
        ticketStatus.setTicket(this);
        return this;
    }

    public Ticket removeTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatuses.remove(ticketStatus);
        ticketStatus.setTicket(null);
        return this;
    }

    public void setTicketStatuses(Set<TicketStatus> ticketStatuses) {
        this.ticketStatuses = ticketStatuses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ticket)) {
            return false;
        }
        return id != null && id.equals(((Ticket) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ticket{" +
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
