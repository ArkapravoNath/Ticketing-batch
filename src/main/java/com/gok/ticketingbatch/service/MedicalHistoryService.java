package com.gok.ticketingbatch.service;

import com.gok.ticketingbatch.service.dto.MedicalHistoryDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.ticketingbatch.domain.MedicalHistory}.
 */
public interface MedicalHistoryService {

    /**
     * Save a medicalHistory.
     *
     * @param medicalHistoryDTO the entity to save.
     * @return the persisted entity.
     */
    MedicalHistoryDTO save(MedicalHistoryDTO medicalHistoryDTO);

    /**
     * Get all the medicalHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MedicalHistoryDTO> findAll(Pageable pageable);


    /**
     * Get the "id" medicalHistory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MedicalHistoryDTO> findOne(Long id);

    /**
     * Delete the "id" medicalHistory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
