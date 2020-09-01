package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.service.RelativeContactService;
import com.gok.ticketingbatch.web.rest.errors.BadRequestAlertException;
import com.gok.ticketingbatch.service.dto.RelativeContactDTO;

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
 * REST controller for managing {@link com.gok.ticketingbatch.domain.RelativeContact}.
 */
@RestController
@RequestMapping("/api")
public class RelativeContactResource {

    private final Logger log = LoggerFactory.getLogger(RelativeContactResource.class);

    private static final String ENTITY_NAME = "ticketingBatchRelativeContact";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RelativeContactService relativeContactService;

    public RelativeContactResource(RelativeContactService relativeContactService) {
        this.relativeContactService = relativeContactService;
    }

    /**
     * {@code POST  /relative-contacts} : Create a new relativeContact.
     *
     * @param relativeContactDTO the relativeContactDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new relativeContactDTO, or with status {@code 400 (Bad Request)} if the relativeContact has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/relative-contacts")
    public ResponseEntity<RelativeContactDTO> createRelativeContact(@RequestBody RelativeContactDTO relativeContactDTO) throws URISyntaxException {
        log.debug("REST request to save RelativeContact : {}", relativeContactDTO);
        if (relativeContactDTO.getId() != null) {
            throw new BadRequestAlertException("A new relativeContact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RelativeContactDTO result = relativeContactService.save(relativeContactDTO);
        return ResponseEntity.created(new URI("/api/relative-contacts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /relative-contacts} : Updates an existing relativeContact.
     *
     * @param relativeContactDTO the relativeContactDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated relativeContactDTO,
     * or with status {@code 400 (Bad Request)} if the relativeContactDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the relativeContactDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/relative-contacts")
    public ResponseEntity<RelativeContactDTO> updateRelativeContact(@RequestBody RelativeContactDTO relativeContactDTO) throws URISyntaxException {
        log.debug("REST request to update RelativeContact : {}", relativeContactDTO);
        if (relativeContactDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RelativeContactDTO result = relativeContactService.save(relativeContactDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, relativeContactDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /relative-contacts} : get all the relativeContacts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of relativeContacts in body.
     */
    @GetMapping("/relative-contacts")
    public ResponseEntity<List<RelativeContactDTO>> getAllRelativeContacts(Pageable pageable) {
        log.debug("REST request to get a page of RelativeContacts");
        Page<RelativeContactDTO> page = relativeContactService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /relative-contacts/:id} : get the "id" relativeContact.
     *
     * @param id the id of the relativeContactDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the relativeContactDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/relative-contacts/{id}")
    public ResponseEntity<RelativeContactDTO> getRelativeContact(@PathVariable Long id) {
        log.debug("REST request to get RelativeContact : {}", id);
        Optional<RelativeContactDTO> relativeContactDTO = relativeContactService.findOne(id);
        return ResponseUtil.wrapOrNotFound(relativeContactDTO);
    }

    /**
     * {@code DELETE  /relative-contacts/:id} : delete the "id" relativeContact.
     *
     * @param id the id of the relativeContactDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/relative-contacts/{id}")
    public ResponseEntity<Void> deleteRelativeContact(@PathVariable Long id) {
        log.debug("REST request to delete RelativeContact : {}", id);
        relativeContactService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
