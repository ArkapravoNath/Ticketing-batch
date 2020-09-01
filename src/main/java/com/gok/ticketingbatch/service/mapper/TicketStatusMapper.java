package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.TicketStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TicketStatus} and its DTO {@link TicketStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface TicketStatusMapper extends EntityMapper<TicketStatusDTO, TicketStatus> {

    @Mapping(source = "ticket.id", target = "ticketId")
    TicketStatusDTO toDto(TicketStatus ticketStatus);

    @Mapping(source = "ticketId", target = "ticket")
    TicketStatus toEntity(TicketStatusDTO ticketStatusDTO);

    default TicketStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        TicketStatus ticketStatus = new TicketStatus();
        ticketStatus.setId(id);
        return ticketStatus;
    }
}
