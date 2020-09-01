package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.service.MobileService;
import com.gok.ticketingbatch.web.rest.errors.BadRequestAlertException;
import com.gok.ticketingbatch.service.dto.MobileDTO;

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
 * REST controller for managing {@link com.gok.ticketingbatch.domain.Mobile}.
 */
@RestController
@RequestMapping("/api")
public class MobileResource {

    private final Logger log = LoggerFactory.getLogger(MobileResource.class);

    private static final String ENTITY_NAME = "ticketingBatchMobile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MobileService mobileService;

    public MobileResource(MobileService mobileService) {
        this.mobileService = mobileService;
    }

    /**
     * {@code POST  /mobiles} : Create a new mobile.
     *
     * @param mobileDTO the mobileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mobileDTO, or with status {@code 400 (Bad Request)} if the mobile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mobiles")
    public ResponseEntity<MobileDTO> createMobile(@RequestBody MobileDTO mobileDTO) throws URISyntaxException {
        log.debug("REST request to save Mobile : {}", mobileDTO);
        if (mobileDTO.getId() != null) {
            throw new BadRequestAlertException("A new mobile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MobileDTO result = mobileService.save(mobileDTO);
        return ResponseEntity.created(new URI("/api/mobiles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mobiles} : Updates an existing mobile.
     *
     * @param mobileDTO the mobileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mobileDTO,
     * or with status {@code 400 (Bad Request)} if the mobileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mobileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mobiles")
    public ResponseEntity<MobileDTO> updateMobile(@RequestBody MobileDTO mobileDTO) throws URISyntaxException {
        log.debug("REST request to update Mobile : {}", mobileDTO);
        if (mobileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MobileDTO result = mobileService.save(mobileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mobileDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mobiles} : get all the mobiles.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mobiles in body.
     */
    @GetMapping("/mobiles")
    public ResponseEntity<List<MobileDTO>> getAllMobiles(Pageable pageable) {
        log.debug("REST request to get a page of Mobiles");
        Page<MobileDTO> page = mobileService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mobiles/:id} : get the "id" mobile.
     *
     * @param id the id of the mobileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mobileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mobiles/{id}")
    public ResponseEntity<MobileDTO> getMobile(@PathVariable Long id) {
        log.debug("REST request to get Mobile : {}", id);
        Optional<MobileDTO> mobileDTO = mobileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mobileDTO);
    }

    /**
     * {@code DELETE  /mobiles/:id} : delete the "id" mobile.
     *
     * @param id the id of the mobileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mobiles/{id}")
    public ResponseEntity<Void> deleteMobile(@PathVariable Long id) {
        log.debug("REST request to delete Mobile : {}", id);
        mobileService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
