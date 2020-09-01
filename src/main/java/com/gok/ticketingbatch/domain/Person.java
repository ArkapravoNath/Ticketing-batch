package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Person extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private String age;

    @Column(name = "dob")
    private Instant dob;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "citizen_ship")
    private String citizenShip;

    @Column(name = "primary_telephone_no")
    private String primaryTelephoneNo;

    @Column(name = "seconday_telephone_no")
    private String secondayTelephoneNo;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "occupation_name")
    private String occupationName;

    @Column(name = "alternate_number")
    private String alternateNumber;

    @Column(name = "language")
    private String language;

    @Column(name = "locale")
    private String locale;

    @Column(name = "family_head_name")
    private String familyHeadName;

    @Column(name = "family_head_relationship")
    private String familyHeadRelationship;

    @Column(name = "family_adult_count")
    private Long familyAdultCount;

    @Column(name = "guardian_name")
    private String guardianName;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Document> documents = new HashSet<>();

    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Mobile> mobiles = new HashSet<>();

    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<RelativeContact> relativeContacts = new HashSet<>();

    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private MedicalPractitioner medicalPractitioner;

    @ManyToOne
    @JsonIgnoreProperties(value = "people", allowSetters = true)
    private RelativeContact relativeContact;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Person middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public Person fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public Person gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public Person age(String age) {
        this.age = age;
        return this;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Instant getDob() {
        return dob;
    }

    public Person dob(Instant dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(Instant dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public Person nationality(String nationality) {
        this.nationality = nationality;
        return this;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCitizenShip() {
        return citizenShip;
    }

    public Person citizenShip(String citizenShip) {
        this.citizenShip = citizenShip;
        return this;
    }

    public void setCitizenShip(String citizenShip) {
        this.citizenShip = citizenShip;
    }

    public String getPrimaryTelephoneNo() {
        return primaryTelephoneNo;
    }

    public Person primaryTelephoneNo(String primaryTelephoneNo) {
        this.primaryTelephoneNo = primaryTelephoneNo;
        return this;
    }

    public void setPrimaryTelephoneNo(String primaryTelephoneNo) {
        this.primaryTelephoneNo = primaryTelephoneNo;
    }

    public String getSecondayTelephoneNo() {
        return secondayTelephoneNo;
    }

    public Person secondayTelephoneNo(String secondayTelephoneNo) {
        this.secondayTelephoneNo = secondayTelephoneNo;
        return this;
    }

    public void setSecondayTelephoneNo(String secondayTelephoneNo) {
        this.secondayTelephoneNo = secondayTelephoneNo;
    }

    public String getOccupation() {
        return occupation;
    }

    public Person occupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOccupationName() {
        return occupationName;
    }

    public Person occupationName(String occupationName) {
        this.occupationName = occupationName;
        return this;
    }

    public void setOccupationName(String occupationName) {
        this.occupationName = occupationName;
    }

    public String getAlternateNumber() {
        return alternateNumber;
    }

    public Person alternateNumber(String alternateNumber) {
        this.alternateNumber = alternateNumber;
        return this;
    }

    public void setAlternateNumber(String alternateNumber) {
        this.alternateNumber = alternateNumber;
    }

    public String getLanguage() {
        return language;
    }

    public Person language(String language) {
        this.language = language;
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocale() {
        return locale;
    }

    public Person locale(String locale) {
        this.locale = locale;
        return this;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFamilyHeadName() {
        return familyHeadName;
    }

    public Person familyHeadName(String familyHeadName) {
        this.familyHeadName = familyHeadName;
        return this;
    }

    public void setFamilyHeadName(String familyHeadName) {
        this.familyHeadName = familyHeadName;
    }

    public String getFamilyHeadRelationship() {
        return familyHeadRelationship;
    }

    public Person familyHeadRelationship(String familyHeadRelationship) {
        this.familyHeadRelationship = familyHeadRelationship;
        return this;
    }

    public void setFamilyHeadRelationship(String familyHeadRelationship) {
        this.familyHeadRelationship = familyHeadRelationship;
    }

    public Long getFamilyAdultCount() {
        return familyAdultCount;
    }

    public Person familyAdultCount(Long familyAdultCount) {
        this.familyAdultCount = familyAdultCount;
        return this;
    }

    public void setFamilyAdultCount(Long familyAdultCount) {
        this.familyAdultCount = familyAdultCount;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public Person guardianName(String guardianName) {
        this.guardianName = guardianName;
        return this;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getStatus() {
        return status;
    }

    public Person status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Person documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Person addDocument(Document document) {
        this.documents.add(document);
        document.setPerson(this);
        return this;
    }

    public Person removeDocument(Document document) {
        this.documents.remove(document);
        document.setPerson(null);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<Mobile> getMobiles() {
        return mobiles;
    }

    public Person mobiles(Set<Mobile> mobiles) {
        this.mobiles = mobiles;
        return this;
    }

    public Person addMobile(Mobile mobile) {
        this.mobiles.add(mobile);
        mobile.setPerson(this);
        return this;
    }

    public Person removeMobile(Mobile mobile) {
        this.mobiles.remove(mobile);
        mobile.setPerson(null);
        return this;
    }

    public void setMobiles(Set<Mobile> mobiles) {
        this.mobiles = mobiles;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Person addresses(Set<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public Person addAddress(Address address) {
        this.addresses.add(address);
        address.setPerson(this);
        return this;
    }

    public Person removeAddress(Address address) {
        this.addresses.remove(address);
        address.setPerson(null);
        return this;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<RelativeContact> getRelativeContacts() {
        return relativeContacts;
    }

    public Person relativeContacts(Set<RelativeContact> relativeContacts) {
        this.relativeContacts = relativeContacts;
        return this;
    }

    public Person addRelativeContact(RelativeContact relativeContact) {
        this.relativeContacts.add(relativeContact);
        relativeContact.setPerson(this);
        return this;
    }

    public Person removeRelativeContact(RelativeContact relativeContact) {
        this.relativeContacts.remove(relativeContact);
        relativeContact.setPerson(null);
        return this;
    }

    public void setRelativeContacts(Set<RelativeContact> relativeContacts) {
        this.relativeContacts = relativeContacts;
    }

    public MedicalPractitioner getMedicalPractitioner() {
        return medicalPractitioner;
    }

    public Person medicalPractitioner(MedicalPractitioner medicalPractitioner) {
        this.medicalPractitioner = medicalPractitioner;
        return this;
    }

    public void setMedicalPractitioner(MedicalPractitioner medicalPractitioner) {
        this.medicalPractitioner = medicalPractitioner;
    }

    public RelativeContact getRelativeContact() {
        return relativeContact;
    }

    public Person relativeContact(RelativeContact relativeContact) {
        this.relativeContact = relativeContact;
        return this;
    }

    public void setRelativeContact(RelativeContact relativeContact) {
        this.relativeContact = relativeContact;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        return id != null && id.equals(((Person) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", gender='" + getGender() + "'" +
            ", age='" + getAge() + "'" +
            ", dob='" + getDob() + "'" +
            ", nationality='" + getNationality() + "'" +
            ", citizenShip='" + getCitizenShip() + "'" +
            ", primaryTelephoneNo='" + getPrimaryTelephoneNo() + "'" +
            ", secondayTelephoneNo='" + getSecondayTelephoneNo() + "'" +
            ", occupation='" + getOccupation() + "'" +
            ", occupationName='" + getOccupationName() + "'" +
            ", alternateNumber='" + getAlternateNumber() + "'" +
            ", language='" + getLanguage() + "'" +
            ", locale='" + getLocale() + "'" +
            ", familyHeadName='" + getFamilyHeadName() + "'" +
            ", familyHeadRelationship='" + getFamilyHeadRelationship() + "'" +
            ", familyAdultCount=" + getFamilyAdultCount() +
            ", guardianName='" + getGuardianName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
