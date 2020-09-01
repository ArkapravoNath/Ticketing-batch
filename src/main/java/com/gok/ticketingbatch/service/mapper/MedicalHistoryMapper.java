package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.MedicalHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MedicalHistory} and its DTO {@link MedicalHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface MedicalHistoryMapper extends EntityMapper<MedicalHistoryDTO, MedicalHistory> {

    @Mapping(source = "patient.id", target = "patientId")
    MedicalHistoryDTO toDto(MedicalHistory medicalHistory);

    @Mapping(source = "patientId", target = "patient")
    MedicalHistory toEntity(MedicalHistoryDTO medicalHistoryDTO);

    default MedicalHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setId(id);
        return medicalHistory;
    }
}
