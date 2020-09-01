package com.gok.ticketingbatch.service.impl;

import com.gok.ticketingbatch.service.KsrsacAddressService;
import com.gok.ticketingbatch.domain.KsrsacAddress;
import com.gok.ticketingbatch.repository.KsrsacAddressRepository;
import com.gok.ticketingbatch.service.dto.KsrsacAddressDTO;
import com.gok.ticketingbatch.service.mapper.KsrsacAddressMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link KsrsacAddress}.
 */
@Service
@Transactional
public class KsrsacAddressServiceImpl implements KsrsacAddressService {

    private final Logger log = LoggerFactory.getLogger(KsrsacAddressServiceImpl.class);

    private final KsrsacAddressRepository ksrsacAddressRepository;

    private final KsrsacAddressMapper ksrsacAddressMapper;

    public KsrsacAddressServiceImpl(KsrsacAddressRepository ksrsacAddressRepository, KsrsacAddressMapper ksrsacAddressMapper) {
        this.ksrsacAddressRepository = ksrsacAddressRepository;
        this.ksrsacAddressMapper = ksrsacAddressMapper;
    }

    @Override
    public KsrsacAddressDTO save(KsrsacAddressDTO ksrsacAddressDTO) {
        log.debug("Request to save KsrsacAddress : {}", ksrsacAddressDTO);
        KsrsacAddress ksrsacAddress = ksrsacAddressMapper.toEntity(ksrsacAddressDTO);
        ksrsacAddress = ksrsacAddressRepository.save(ksrsacAddress);
        return ksrsacAddressMapper.toDto(ksrsacAddress);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<KsrsacAddressDTO> findAll(Pageable pageable) {
        log.debug("Request to get all KsrsacAddresses");
        return ksrsacAddressRepository.findAll(pageable)
            .map(ksrsacAddressMapper::toDto);
    }



    /**
     *  Get all the ksrsacAddresses where Address is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<KsrsacAddressDTO> findAllWhereAddressIsNull() {
        log.debug("Request to get all ksrsacAddresses where Address is null");
        return StreamSupport
            .stream(ksrsacAddressRepository.findAll().spliterator(), false)
            .filter(ksrsacAddress -> ksrsacAddress.getAddress() == null)
            .map(ksrsacAddressMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<KsrsacAddressDTO> findOne(Long id) {
        log.debug("Request to get KsrsacAddress : {}", id);
        return ksrsacAddressRepository.findById(id)
            .map(ksrsacAddressMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete KsrsacAddress : {}", id);
        ksrsacAddressRepository.deleteById(id);
    }
}
