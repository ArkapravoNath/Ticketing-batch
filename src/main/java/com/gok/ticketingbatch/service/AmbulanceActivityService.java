package com.gok.ticketingbatch.service;

import com.gok.ticketingbatch.service.dto.AmbulanceActivityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.ticketingbatch.domain.AmbulanceActivity}.
 */
public interface AmbulanceActivityService {

    /**
     * Save a ambulanceActivity.
     *
     * @param ambulanceActivityDTO the entity to save.
     * @return the persisted entity.
     */
    AmbulanceActivityDTO save(AmbulanceActivityDTO ambulanceActivityDTO);

    /**
     * Get all the ambulanceActivities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AmbulanceActivityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" ambulanceActivity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AmbulanceActivityDTO> findOne(Long id);

    /**
     * Delete the "id" ambulanceActivity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
