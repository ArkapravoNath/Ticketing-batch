package com.gok.ticketingbatch.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A RelativeContact.
 */
@Entity
@Table(name = "relative_contact")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RelativeContact extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "relationship_type")
    private String relationshipType;

    @OneToMany(mappedBy = "relativeContact")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Person> people = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "relativeContacts", allowSetters = true)
    private Person person;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public RelativeContact type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public RelativeContact relationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
        return this;
    }

    public void setRelationshipType(String relationshipType) {
        this.relationshipType = relationshipType;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public RelativeContact people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public RelativeContact addPerson(Person person) {
        this.people.add(person);
        person.setRelativeContact(this);
        return this;
    }

    public RelativeContact removePerson(Person person) {
        this.people.remove(person);
        person.setRelativeContact(null);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Person getPerson() {
        return person;
    }

    public RelativeContact person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelativeContact)) {
            return false;
        }
        return id != null && id.equals(((RelativeContact) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RelativeContact{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", relationshipType='" + getRelationshipType() + "'" +
            "}";
    }
}
