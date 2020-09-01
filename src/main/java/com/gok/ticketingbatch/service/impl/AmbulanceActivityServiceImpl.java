package com.gok.ticketingbatch.service.impl;

import com.gok.ticketingbatch.service.AmbulanceActivityService;
import com.gok.ticketingbatch.domain.AmbulanceActivity;
import com.gok.ticketingbatch.repository.AmbulanceActivityRepository;
import com.gok.ticketingbatch.service.dto.AmbulanceActivityDTO;
import com.gok.ticketingbatch.service.mapper.AmbulanceActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AmbulanceActivity}.
 */
@Service
@Transactional
public class AmbulanceActivityServiceImpl implements AmbulanceActivityService {

    private final Logger log = LoggerFactory.getLogger(AmbulanceActivityServiceImpl.class);

    private final AmbulanceActivityRepository ambulanceActivityRepository;

    private final AmbulanceActivityMapper ambulanceActivityMapper;

    public AmbulanceActivityServiceImpl(AmbulanceActivityRepository ambulanceActivityRepository, AmbulanceActivityMapper ambulanceActivityMapper) {
        this.ambulanceActivityRepository = ambulanceActivityRepository;
        this.ambulanceActivityMapper = ambulanceActivityMapper;
    }

    @Override
    public AmbulanceActivityDTO save(AmbulanceActivityDTO ambulanceActivityDTO) {
        log.debug("Request to save AmbulanceActivity : {}", ambulanceActivityDTO);
        AmbulanceActivity ambulanceActivity = ambulanceActivityMapper.toEntity(ambulanceActivityDTO);
        ambulanceActivity = ambulanceActivityRepository.save(ambulanceActivity);
        return ambulanceActivityMapper.toDto(ambulanceActivity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AmbulanceActivityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AmbulanceActivities");
        return ambulanceActivityRepository.findAll(pageable)
            .map(ambulanceActivityMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AmbulanceActivityDTO> findOne(Long id) {
        log.debug("Request to get AmbulanceActivity : {}", id);
        return ambulanceActivityRepository.findById(id)
            .map(ambulanceActivityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AmbulanceActivity : {}", id);
        ambulanceActivityRepository.deleteById(id);
    }
}
