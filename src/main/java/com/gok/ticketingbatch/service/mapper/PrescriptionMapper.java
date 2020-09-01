package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.PrescriptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Prescription} and its DTO {@link PrescriptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface PrescriptionMapper extends EntityMapper<PrescriptionDTO, Prescription> {

    @Mapping(source = "ticket.id", target = "ticketId")
    PrescriptionDTO toDto(Prescription prescription);

    @Mapping(source = "ticketId", target = "ticket")
    Prescription toEntity(PrescriptionDTO prescriptionDTO);

    default Prescription fromId(Long id) {
        if (id == null) {
            return null;
        }
        Prescription prescription = new Prescription();
        prescription.setId(id);
        return prescription;
    }
}
