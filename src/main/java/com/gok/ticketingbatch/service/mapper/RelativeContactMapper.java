package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.RelativeContactDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link RelativeContact} and its DTO {@link RelativeContactDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface RelativeContactMapper extends EntityMapper<RelativeContactDTO, RelativeContact> {

    @Mapping(source = "person.id", target = "personId")
    RelativeContactDTO toDto(RelativeContact relativeContact);

    @Mapping(target = "people", ignore = true)
    @Mapping(target = "removePerson", ignore = true)
    @Mapping(source = "personId", target = "person")
    RelativeContact toEntity(RelativeContactDTO relativeContactDTO);

    default RelativeContact fromId(Long id) {
        if (id == null) {
            return null;
        }
        RelativeContact relativeContact = new RelativeContact();
        relativeContact.setId(id);
        return relativeContact;
    }
}
