package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.TicketDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ticket} and its DTO {@link TicketDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TicketMapper extends EntityMapper<TicketDTO, Ticket> {


    @Mapping(target = "ticketActions", ignore = true)
    @Mapping(target = "removeTicketAction", ignore = true)
    @Mapping(target = "prescriptions", ignore = true)
    @Mapping(target = "removePrescription", ignore = true)
    @Mapping(target = "diagnoses", ignore = true)
    @Mapping(target = "removeDiagnosis", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "removeTags", ignore = true)
    @Mapping(target = "ticketStatuses", ignore = true)
    @Mapping(target = "removeTicketStatus", ignore = true)
    Ticket toEntity(TicketDTO ticketDTO);

    default Ticket fromId(Long id) {
        if (id == null) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticket.setId(id);
        return ticket;
    }
}
