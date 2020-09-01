package com.gok.ticketingbatch.service.impl;

import com.gok.ticketingbatch.service.RelativeContactService;
import com.gok.ticketingbatch.domain.RelativeContact;
import com.gok.ticketingbatch.repository.RelativeContactRepository;
import com.gok.ticketingbatch.service.dto.RelativeContactDTO;
import com.gok.ticketingbatch.service.mapper.RelativeContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link RelativeContact}.
 */
@Service
@Transactional
public class RelativeContactServiceImpl implements RelativeContactService {

    private final Logger log = LoggerFactory.getLogger(RelativeContactServiceImpl.class);

    private final RelativeContactRepository relativeContactRepository;

    private final RelativeContactMapper relativeContactMapper;

    public RelativeContactServiceImpl(RelativeContactRepository relativeContactRepository, RelativeContactMapper relativeContactMapper) {
        this.relativeContactRepository = relativeContactRepository;
        this.relativeContactMapper = relativeContactMapper;
    }

    @Override
    public RelativeContactDTO save(RelativeContactDTO relativeContactDTO) {
        log.debug("Request to save RelativeContact : {}", relativeContactDTO);
        RelativeContact relativeContact = relativeContactMapper.toEntity(relativeContactDTO);
        relativeContact = relativeContactRepository.save(relativeContact);
        return relativeContactMapper.toDto(relativeContact);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelativeContactDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RelativeContacts");
        return relativeContactRepository.findAll(pageable)
            .map(relativeContactMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RelativeContactDTO> findOne(Long id) {
        log.debug("Request to get RelativeContact : {}", id);
        return relativeContactRepository.findById(id)
            .map(relativeContactMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RelativeContact : {}", id);
        relativeContactRepository.deleteById(id);
    }
}
