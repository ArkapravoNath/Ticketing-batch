package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {KsrsacAddressMapper.class, PersonMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Mapping(source = "ksrsacAddress.id", target = "ksrsacAddressId")
    @Mapping(source = "person.id", target = "personId")
    AddressDTO toDto(Address address);

    @Mapping(source = "ksrsacAddressId", target = "ksrsacAddress")
    @Mapping(source = "personId", target = "person")
    Address toEntity(AddressDTO addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
