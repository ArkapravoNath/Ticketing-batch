package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.DiagnosisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Diagnosis} and its DTO {@link DiagnosisDTO}.
 */
@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface DiagnosisMapper extends EntityMapper<DiagnosisDTO, Diagnosis> {

    @Mapping(source = "ticket.id", target = "ticketId")
    DiagnosisDTO toDto(Diagnosis diagnosis);

    @Mapping(source = "ticketId", target = "ticket")
    Diagnosis toEntity(DiagnosisDTO diagnosisDTO);

    default Diagnosis fromId(Long id) {
        if (id == null) {
            return null;
        }
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setId(id);
        return diagnosis;
    }
}
