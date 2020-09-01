package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.TicketActionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TicketAction} and its DTO {@link TicketActionDTO}.
 */
@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface TicketActionMapper extends EntityMapper<TicketActionDTO, TicketAction> {

    @Mapping(source = "ticket.id", target = "ticketId")
    TicketActionDTO toDto(TicketAction ticketAction);

    @Mapping(target = "assignments", ignore = true)
    @Mapping(target = "removeAssignment", ignore = true)
    @Mapping(source = "ticketId", target = "ticket")
    TicketAction toEntity(TicketActionDTO ticketActionDTO);

    default TicketAction fromId(Long id) {
        if (id == null) {
            return null;
        }
        TicketAction ticketAction = new TicketAction();
        ticketAction.setId(id);
        return ticketAction;
    }
}
