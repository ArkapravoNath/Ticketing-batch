package com.gok.ticketingbatch.web.rest;

import com.gok.ticketingbatch.service.KsrsacAddressService;
import com.gok.ticketingbatch.web.rest.errors.BadRequestAlertException;
import com.gok.ticketingbatch.service.dto.KsrsacAddressDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.gok.ticketingbatch.domain.KsrsacAddress}.
 */
@RestController
@RequestMapping("/api")
public class KsrsacAddressResource {

    private final Logger log = LoggerFactory.getLogger(KsrsacAddressResource.class);

    private static final String ENTITY_NAME = "ticketingBatchKsrsacAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KsrsacAddressService ksrsacAddressService;

    public KsrsacAddressResource(KsrsacAddressService ksrsacAddressService) {
        this.ksrsacAddressService = ksrsacAddressService;
    }

    /**
     * {@code POST  /ksrsac-addresses} : Create a new ksrsacAddress.
     *
     * @param ksrsacAddressDTO the ksrsacAddressDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ksrsacAddressDTO, or with status {@code 400 (Bad Request)} if the ksrsacAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ksrsac-addresses")
    public ResponseEntity<KsrsacAddressDTO> createKsrsacAddress(@RequestBody KsrsacAddressDTO ksrsacAddressDTO) throws URISyntaxException {
        log.debug("REST request to save KsrsacAddress : {}", ksrsacAddressDTO);
        if (ksrsacAddressDTO.getId() != null) {
            throw new BadRequestAlertException("A new ksrsacAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KsrsacAddressDTO result = ksrsacAddressService.save(ksrsacAddressDTO);
        return ResponseEntity.created(new URI("/api/ksrsac-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ksrsac-addresses} : Updates an existing ksrsacAddress.
     *
     * @param ksrsacAddressDTO the ksrsacAddressDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ksrsacAddressDTO,
     * or with status {@code 400 (Bad Request)} if the ksrsacAddressDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ksrsacAddressDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ksrsac-addresses")
    public ResponseEntity<KsrsacAddressDTO> updateKsrsacAddress(@RequestBody KsrsacAddressDTO ksrsacAddressDTO) throws URISyntaxException {
        log.debug("REST request to update KsrsacAddress : {}", ksrsacAddressDTO);
        if (ksrsacAddressDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KsrsacAddressDTO result = ksrsacAddressService.save(ksrsacAddressDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ksrsacAddressDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ksrsac-addresses} : get all the ksrsacAddresses.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ksrsacAddresses in body.
     */
    @GetMapping("/ksrsac-addresses")
    public ResponseEntity<List<KsrsacAddressDTO>> getAllKsrsacAddresses(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("address-is-null".equals(filter)) {
            log.debug("REST request to get all KsrsacAddresss where address is null");
            return new ResponseEntity<>(ksrsacAddressService.findAllWhereAddressIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of KsrsacAddresses");
        Page<KsrsacAddressDTO> page = ksrsacAddressService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ksrsac-addresses/:id} : get the "id" ksrsacAddress.
     *
     * @param id the id of the ksrsacAddressDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ksrsacAddressDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ksrsac-addresses/{id}")
    public ResponseEntity<KsrsacAddressDTO> getKsrsacAddress(@PathVariable Long id) {
        log.debug("REST request to get KsrsacAddress : {}", id);
        Optional<KsrsacAddressDTO> ksrsacAddressDTO = ksrsacAddressService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ksrsacAddressDTO);
    }

    /**
     * {@code DELETE  /ksrsac-addresses/:id} : delete the "id" ksrsacAddress.
     *
     * @param id the id of the ksrsacAddressDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ksrsac-addresses/{id}")
    public ResponseEntity<Void> deleteKsrsacAddress(@PathVariable Long id) {
        log.debug("REST request to delete KsrsacAddress : {}", id);
        ksrsacAddressService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
