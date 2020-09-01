package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.KsrsacAddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link KsrsacAddress} and its DTO {@link KsrsacAddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KsrsacAddressMapper extends EntityMapper<KsrsacAddressDTO, KsrsacAddress> {


    @Mapping(target = "address", ignore = true)
    KsrsacAddress toEntity(KsrsacAddressDTO ksrsacAddressDTO);

    default KsrsacAddress fromId(Long id) {
        if (id == null) {
            return null;
        }
        KsrsacAddress ksrsacAddress = new KsrsacAddress();
        ksrsacAddress.setId(id);
        return ksrsacAddress;
    }
}
