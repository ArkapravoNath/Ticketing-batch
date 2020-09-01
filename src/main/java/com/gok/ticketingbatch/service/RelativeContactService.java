package com.gok.ticketingbatch.service;

import com.gok.ticketingbatch.service.dto.RelativeContactDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.ticketingbatch.domain.RelativeContact}.
 */
public interface RelativeContactService {

    /**
     * Save a relativeContact.
     *
     * @param relativeContactDTO the entity to save.
     * @return the persisted entity.
     */
    RelativeContactDTO save(RelativeContactDTO relativeContactDTO);

    /**
     * Get all the relativeContacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RelativeContactDTO> findAll(Pageable pageable);


    /**
     * Get the "id" relativeContact.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RelativeContactDTO> findOne(Long id);

    /**
     * Delete the "id" relativeContact.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
