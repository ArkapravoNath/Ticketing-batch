package com.gok.ticketingbatch.service.mapper;


import com.gok.ticketingbatch.domain.*;
import com.gok.ticketingbatch.service.dto.TagsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tags} and its DTO {@link TagsDTO}.
 */
@Mapper(componentModel = "spring", uses = {TicketMapper.class})
public interface TagsMapper extends EntityMapper<TagsDTO, Tags> {

    @Mapping(source = "ticket.id", target = "ticketId")
    TagsDTO toDto(Tags tags);

    @Mapping(source = "ticketId", target = "ticket")
    Tags toEntity(TagsDTO tagsDTO);

    default Tags fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tags tags = new Tags();
        tags.setId(id);
        return tags;
    }
}
