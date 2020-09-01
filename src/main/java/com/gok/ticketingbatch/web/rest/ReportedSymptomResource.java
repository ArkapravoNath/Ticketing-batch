package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.service.ReportedSymptomService;
import com.gok.ticketingbatch.web.rest.errors.BadRequestAlertException;
import com.gok.ticketingbatch.service.dto.ReportedSymptomDTO;

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
 * REST controller for managing {@link com.gok.ticketingbatch.domain.ReportedSymptom}.
 */
@RestController
@RequestMapping("/api")
public class ReportedSymptomResource {

    private final Logger log = LoggerFactory.getLogger(ReportedSymptomResource.class);

    private static final String ENTITY_NAME = "ticketingBatchReportedSymptom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReportedSymptomService reportedSymptomService;

    public ReportedSymptomResource(ReportedSymptomService reportedSymptomService) {
        this.reportedSymptomService = reportedSymptomService;
    }

    /**
     * {@code POST  /reported-symptoms} : Create a new reportedSymptom.
     *
     * @param reportedSymptomDTO the reportedSymptomDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reportedSymptomDTO, or with status {@code 400 (Bad Request)} if the reportedSymptom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reported-symptoms")
    public ResponseEntity<ReportedSymptomDTO> createReportedSymptom(@RequestBody ReportedSymptomDTO reportedSymptomDTO) throws URISyntaxException {
        log.debug("REST request to save ReportedSymptom : {}", reportedSymptomDTO);
        if (reportedSymptomDTO.getId() != null) {
            throw new BadRequestAlertException("A new reportedSymptom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportedSymptomDTO result = reportedSymptomService.save(reportedSymptomDTO);
        return ResponseEntity.created(new URI("/api/reported-symptoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reported-symptoms} : Updates an existing reportedSymptom.
     *
     * @param reportedSymptomDTO the reportedSymptomDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reportedSymptomDTO,
     * or with status {@code 400 (Bad Request)} if the reportedSymptomDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reportedSymptomDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reported-symptoms")
    public ResponseEntity<ReportedSymptomDTO> updateReportedSymptom(@RequestBody ReportedSymptomDTO reportedSymptomDTO) throws URISyntaxException {
        log.debug("REST request to update ReportedSymptom : {}", reportedSymptomDTO);
        if (reportedSymptomDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReportedSymptomDTO result = reportedSymptomService.save(reportedSymptomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reportedSymptomDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reported-symptoms} : get all the reportedSymptoms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reportedSymptoms in body.
     */
    @GetMapping("/reported-symptoms")
    public ResponseEntity<List<ReportedSymptomDTO>> getAllReportedSymptoms(Pageable pageable) {
        log.debug("REST request to get a page of ReportedSymptoms");
        Page<ReportedSymptomDTO> page = reportedSymptomService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reported-symptoms/:id} : get the "id" reportedSymptom.
     *
     * @param id the id of the reportedSymptomDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reportedSymptomDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reported-symptoms/{id}")
    public ResponseEntity<ReportedSymptomDTO> getReportedSymptom(@PathVariable Long id) {
        log.debug("REST request to get ReportedSymptom : {}", id);
        Optional<ReportedSymptomDTO> reportedSymptomDTO = reportedSymptomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reportedSymptomDTO);
    }

    /**
     * {@code DELETE  /reported-symptoms/:id} : delete the "id" reportedSymptom.
     *
     * @param id the id of the reportedSymptomDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reported-symptoms/{id}")
    public ResponseEntity<Void> deleteReportedSymptom(@PathVariable Long id) {
        log.debug("REST request to delete ReportedSymptom : {}", id);
        reportedSymptomService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
