package com.gok.ticketingbatch.service;

import com.gok.ticketingbatch.service.dto.PatientActivityDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.ticketingbatch.domain.PatientActivity}.
 */
public interface PatientActivityService {

    /**
     * Save a patientActivity.
     *
     * @param patientActivityDTO the entity to save.
     * @return the persisted entity.
     */
    PatientActivityDTO save(PatientActivityDTO patientActivityDTO);

    /**
     * Get all the patientActivities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PatientActivityDTO> findAll(Pageable pageable);


    /**
     * Get the "id" patientActivity.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PatientActivityDTO> findOne(Long id);

    /**
     * Delete the "id" patientActivity.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
