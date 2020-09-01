package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.AssignmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Assignment} and its DTO {@link AssignmentDTO}.
 */
@Mapper(componentModel = "spring", uses = {TicketActionMapper.class})
public interface AssignmentMapper extends EntityMapper<AssignmentDTO, Assignment> {

    @Mapping(source = "ticketAction.id", target = "ticketActionId")
    AssignmentDTO toDto(Assignment assignment);

    @Mapping(source = "ticketActionId", target = "ticketAction")
    Assignment toEntity(AssignmentDTO assignmentDTO);

    default Assignment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Assignment assignment = new Assignment();
        assignment.setId(id);
        return assignment;
    }
}
