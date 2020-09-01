package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.PatientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Patient} and its DTO {@link PatientDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PatientMapper extends EntityMapper<PatientDTO, Patient> {


    @Mapping(target = "medicalHistories", ignore = true)
    @Mapping(target = "removeMedicalHistory", ignore = true)
    @Mapping(target = "patientActivities", ignore = true)
    @Mapping(target = "removePatientActivity", ignore = true)
    @Mapping(target = "reportedSymptoms", ignore = true)
    @Mapping(target = "removeReportedSymptom", ignore = true)
    Patient toEntity(PatientDTO patientDTO);

    default Patient fromId(Long id) {
        if (id == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setId(id);
        return patient;
    }
}
