package com.gok.ticketingbatch.service;

import com.gok.ticketingbatch.service.dto.MobileDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.ticketingbatch.domain.Mobile}.
 */
public interface MobileService {

    /**
     * Save a mobile.
     *
     * @param mobileDTO the entity to save.
     * @return the persisted entity.
     */
    MobileDTO save(MobileDTO mobileDTO);

    /**
     * Get all the mobiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MobileDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mobile.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MobileDTO> findOne(Long id);

    /**
     * Delete the "id" mobile.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
