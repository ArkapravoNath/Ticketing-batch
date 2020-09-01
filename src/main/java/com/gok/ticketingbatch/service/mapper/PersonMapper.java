package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.PersonDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Person} and its DTO {@link PersonDTO}.
 */
@Mapper(componentModel = "spring", uses = {RelativeContactMapper.class})
public interface PersonMapper extends EntityMapper<PersonDTO, Person> {

    @Mapping(source = "relativeContact.id", target = "relativeContactId")
    PersonDTO toDto(Person person);

    @Mapping(target = "documents", ignore = true)
    @Mapping(target = "removeDocument", ignore = true)
    @Mapping(target = "mobiles", ignore = true)
    @Mapping(target = "removeMobile", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "removeAddress", ignore = true)
    @Mapping(target = "relativeContacts", ignore = true)
    @Mapping(target = "removeRelativeContact", ignore = true)
    @Mapping(target = "medicalPractitioner", ignore = true)
    @Mapping(source = "relativeContactId", target = "relativeContact")
    Person toEntity(PersonDTO personDTO);

    default Person fromId(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
