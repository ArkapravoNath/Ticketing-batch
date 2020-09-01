package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.PatientActivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PatientActivity} and its DTO {@link PatientActivityDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface PatientActivityMapper extends EntityMapper<PatientActivityDTO, PatientActivity> {

    @Mapping(source = "patient.id", target = "patientId")
    PatientActivityDTO toDto(PatientActivity patientActivity);

    @Mapping(target = "ambulanceActivities", ignore = true)
    @Mapping(target = "removeAmbulanceActivity", ignore = true)
    @Mapping(source = "patientId", target = "patient")
    PatientActivity toEntity(PatientActivityDTO patientActivityDTO);

    default PatientActivity fromId(Long id) {
        if (id == null) {
            return null;
        }
        PatientActivity patientActivity = new PatientActivity();
        patientActivity.setId(id);
        return patientActivity;
    }
}
