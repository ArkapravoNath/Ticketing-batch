package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.service.PatientActivityService;
import com.gok.ticketingbatch.web.rest.errors.BadRequestAlertException;
import com.gok.ticketingbatch.service.dto.PatientActivityDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.gok.ticketingbatch.domain.PatientActivity}.
 */
@RestController
@RequestMapping("/api")
public class PatientActivityResource {

    private final Logger log = LoggerFactory.getLogger(PatientActivityResource.class);

    private static final String ENTITY_NAME = "ticketingBatchPatientActivity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientActivityService patientActivityService;

    public PatientActivityResource(PatientActivityService patientActivityService) {
        this.patientActivityService = patientActivityService;
    }

    /**
     * {@code POST  /patient-activities} : Create a new patientActivity.
     *
     * @param patientActivityDTO the patientActivityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patientActivityDTO, or with status {@code 400 (Bad Request)} if the patientActivity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/patient-activities")
    public ResponseEntity<PatientActivityDTO> createPatientActivity(@RequestBody PatientActivityDTO patientActivityDTO) throws URISyntaxException {
        log.debug("REST request to save PatientActivity : {}", patientActivityDTO);
        if (patientActivityDTO.getId() != null) {
            throw new BadRequestAlertException("A new patientActivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PatientActivityDTO result = patientActivityService.save(patientActivityDTO);
        return ResponseEntity.created(new URI("/api/patient-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /patient-activities} : Updates an existing patientActivity.
     *
     * @param patientActivityDTO the patientActivityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patientActivityDTO,
     * or with status {@code 400 (Bad Request)} if the patientActivityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patientActivityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/patient-activities")
    public ResponseEntity<PatientActivityDTO> updatePatientActivity(@RequestBody PatientActivityDTO patientActivityDTO) throws URISyntaxException {
        log.debug("REST request to update PatientActivity : {}", patientActivityDTO);
        if (patientActivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PatientActivityDTO result = patientActivityService.save(patientActivityDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, patientActivityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /patient-activities} : get all the patientActivities.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patientActivities in body.
     */
    @GetMapping("/patient-activities")
    public ResponseEntity<List<PatientActivityDTO>> getAllPatientActivities(Pageable pageable) {
        log.debug("REST request to get a page of PatientActivities");
        Page<PatientActivityDTO> page = patientActivityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /patient-activities/:id} : get the "id" patientActivity.
     *
     * @param id the id of the patientActivityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patientActivityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/patient-activities/{id}")
    public ResponseEntity<PatientActivityDTO> getPatientActivity(@PathVariable Long id) {
        log.debug("REST request to get PatientActivity : {}", id);
        Optional<PatientActivityDTO> patientActivityDTO = patientActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(patientActivityDTO);
    }

    /**
     * {@code DELETE  /patient-activities/:id} : delete the "id" patientActivity.
     *
     * @param id the id of the patientActivityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/patient-activities/{id}")
    public ResponseEntity<Void> deletePatientActivity(@PathVariable Long id) {
        log.debug("REST request to delete PatientActivity : {}", id);
        patientActivityService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
