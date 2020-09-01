package com.gok.ticketingbatch.service;

import com.gok.ticketingbatch.service.dto.TicketStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.ticketingbatch.domain.TicketStatus}.
 */
public interface TicketStatusService {

    /**
     * Save a ticketStatus.
     *
     * @param ticketStatusDTO the entity to save.
     * @return the persisted entity.
     */
    TicketStatusDTO save(TicketStatusDTO ticketStatusDTO);

    /**
     * Get all the ticketStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TicketStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ticketStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TicketStatusDTO> findOne(Long id);

    /**
     * Delete the "id" ticketStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
