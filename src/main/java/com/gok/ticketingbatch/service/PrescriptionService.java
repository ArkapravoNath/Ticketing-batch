package com.gok.ticketingbatch.service;

import com.gok.ticketingbatch.service.dto.PrescriptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.ticketingbatch.domain.Prescription}.
 */
public interface PrescriptionService {

    /**
     * Save a prescription.
     *
     * @param prescriptionDTO the entity to save.
     * @return the persisted entity.
     */
    PrescriptionDTO save(PrescriptionDTO prescriptionDTO);

    /**
     * Get all the prescriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PrescriptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" prescription.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PrescriptionDTO> findOne(Long id);

    /**
     * Delete the "id" prescription.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
