package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.AmbulanceActivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AmbulanceActivity} and its DTO {@link AmbulanceActivityDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientActivityMapper.class})
public interface AmbulanceActivityMapper extends EntityMapper<AmbulanceActivityDTO, AmbulanceActivity> {

    @Mapping(source = "patientActivity.id", target = "patientActivityId")
    AmbulanceActivityDTO toDto(AmbulanceActivity ambulanceActivity);

    @Mapping(source = "patientActivityId", target = "patientActivity")
    AmbulanceActivity toEntity(AmbulanceActivityDTO ambulanceActivityDTO);

    default AmbulanceActivity fromId(Long id) {
        if (id == null) {
            return null;
        }
        AmbulanceActivity ambulanceActivity = new AmbulanceActivity();
        ambulanceActivity.setId(id);
        return ambulanceActivity;
    }
}
