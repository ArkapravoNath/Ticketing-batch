package com.gok.ticketingbatch.service;

import com.gok.ticketingbatch.service.dto.KsrsacAddressDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.ticketingbatch.domain.KsrsacAddress}.
 */
public interface KsrsacAddressService {

    /**
     * Save a ksrsacAddress.
     *
     * @param ksrsacAddressDTO the entity to save.
     * @return the persisted entity.
     */
    KsrsacAddressDTO save(KsrsacAddressDTO ksrsacAddressDTO);

    /**
     * Get all the ksrsacAddresses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KsrsacAddressDTO> findAll(Pageable pageable);
    /**
     * Get all the KsrsacAddressDTO where Address is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<KsrsacAddressDTO> findAllWhereAddressIsNull();


    /**
     * Get the "id" ksrsacAddress.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KsrsacAddressDTO> findOne(Long id);

    /**
     * Delete the "id" ksrsacAddress.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
