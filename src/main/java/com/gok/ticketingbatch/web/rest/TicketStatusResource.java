package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.service.TicketStatusService;
import com.gok.ticketingbatch.web.rest.errors.BadRequestAlertException;
import com.gok.ticketingbatch.service.dto.TicketStatusDTO;

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
 * REST controller for managing {@link com.gok.ticketingbatch.domain.TicketStatus}.
 */
@RestController
@RequestMapping("/api")
public class TicketStatusResource {

    private final Logger log = LoggerFactory.getLogger(TicketStatusResource.class);

    private static final String ENTITY_NAME = "ticketingBatchTicketStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TicketStatusService ticketStatusService;

    public TicketStatusResource(TicketStatusService ticketStatusService) {
        this.ticketStatusService = ticketStatusService;
    }

    /**
     * {@code POST  /ticket-statuses} : Create a new ticketStatus.
     *
     * @param ticketStatusDTO the ticketStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ticketStatusDTO, or with status {@code 400 (Bad Request)} if the ticketStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ticket-statuses")
    public ResponseEntity<TicketStatusDTO> createTicketStatus(@RequestBody TicketStatusDTO ticketStatusDTO) throws URISyntaxException {
        log.debug("REST request to save TicketStatus : {}", ticketStatusDTO);
        if (ticketStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new ticketStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TicketStatusDTO result = ticketStatusService.save(ticketStatusDTO);
        return ResponseEntity.created(new URI("/api/ticket-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ticket-statuses} : Updates an existing ticketStatus.
     *
     * @param ticketStatusDTO the ticketStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ticketStatusDTO,
     * or with status {@code 400 (Bad Request)} if the ticketStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ticketStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ticket-statuses")
    public ResponseEntity<TicketStatusDTO> updateTicketStatus(@RequestBody TicketStatusDTO ticketStatusDTO) throws URISyntaxException {
        log.debug("REST request to update TicketStatus : {}", ticketStatusDTO);
        if (ticketStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TicketStatusDTO result = ticketStatusService.save(ticketStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ticketStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ticket-statuses} : get all the ticketStatuses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ticketStatuses in body.
     */
    @GetMapping("/ticket-statuses")
    public ResponseEntity<List<TicketStatusDTO>> getAllTicketStatuses(Pageable pageable) {
        log.debug("REST request to get a page of TicketStatuses");
        Page<TicketStatusDTO> page = ticketStatusService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ticket-statuses/:id} : get the "id" ticketStatus.
     *
     * @param id the id of the ticketStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ticketStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ticket-statuses/{id}")
    public ResponseEntity<TicketStatusDTO> getTicketStatus(@PathVariable Long id) {
        log.debug("REST request to get TicketStatus : {}", id);
        Optional<TicketStatusDTO> ticketStatusDTO = ticketStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ticketStatusDTO);
    }

    /**
     * {@code DELETE  /ticket-statuses/:id} : delete the "id" ticketStatus.
     *
     * @param id the id of the ticketStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ticket-statuses/{id}")
    public ResponseEntity<Void> deleteTicketStatus(@PathVariable Long id) {
        log.debug("REST request to delete TicketStatus : {}", id);
        ticketStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
