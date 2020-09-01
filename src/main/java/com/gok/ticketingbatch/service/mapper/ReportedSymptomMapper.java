package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.ReportedSymptomDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReportedSymptom} and its DTO {@link ReportedSymptomDTO}.
 */
@Mapper(componentModel = "spring", uses = {PatientMapper.class})
public interface ReportedSymptomMapper extends EntityMapper<ReportedSymptomDTO, ReportedSymptom> {

    @Mapping(source = "patient.id", target = "patientId")
    ReportedSymptomDTO toDto(ReportedSymptom reportedSymptom);

    @Mapping(source = "patientId", target = "patient")
    ReportedSymptom toEntity(ReportedSymptomDTO reportedSymptomDTO);

    default ReportedSymptom fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReportedSymptom reportedSymptom = new ReportedSymptom();
        reportedSymptom.setId(id);
        return reportedSymptom;
    }
}
