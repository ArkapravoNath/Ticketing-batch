package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.MobileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Mobile} and its DTO {@link MobileDTO}.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface MobileMapper extends EntityMapper<MobileDTO, Mobile> {

    @Mapping(source = "person.id", target = "personId")
    MobileDTO toDto(Mobile mobile);

    @Mapping(source = "personId", target = "person")
    Mobile toEntity(MobileDTO mobileDTO);

    default Mobile fromId(Long id) {
        if (id == null) {
            return null;
        }
        Mobile mobile = new Mobile();
        mobile.setId(id);
        return mobile;
    }
}
