package com.gok.ticketingbatch.service;

import com.gok.ticketingbatch.service.dto.ReportedSymptomDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.ticketingbatch.domain.ReportedSymptom}.
 */
public interface ReportedSymptomService {

    /**
     * Save a reportedSymptom.
     *
     * @param reportedSymptomDTO the entity to save.
     * @return the persisted entity.
     */
    ReportedSymptomDTO save(ReportedSymptomDTO reportedSymptomDTO);

    /**
     * Get all the reportedSymptoms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReportedSymptomDTO> findAll(Pageable pageable);


    /**
     * Get the "id" reportedSymptom.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReportedSymptomDTO> findOne(Long id);

    /**
     * Delete the "id" reportedSymptom.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
