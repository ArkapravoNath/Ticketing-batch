package com.gok.ticketingbatch.service.impl;

import com.gok.ticketingbatch.service.PatientActivityService;
import com.gok.ticketingbatch.domain.PatientActivity;
import com.gok.ticketingbatch.repository.PatientActivityRepository;
import com.gok.ticketingbatch.service.dto.PatientActivityDTO;
import com.gok.ticketingbatch.service.mapper.PatientActivityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PatientActivity}.
 */
@Service
@Transactional
public class PatientActivityServiceImpl implements PatientActivityService {

    private final Logger log = LoggerFactory.getLogger(PatientActivityServiceImpl.class);

    private final PatientActivityRepository patientActivityRepository;

    private final PatientActivityMapper patientActivityMapper;

    public PatientActivityServiceImpl(PatientActivityRepository patientActivityRepository, PatientActivityMapper patientActivityMapper) {
        this.patientActivityRepository = patientActivityRepository;
        this.patientActivityMapper = patientActivityMapper;
    }

    @Override
    public PatientActivityDTO save(PatientActivityDTO patientActivityDTO) {
        log.debug("Request to save PatientActivity : {}", patientActivityDTO);
        PatientActivity patientActivity = patientActivityMapper.toEntity(patientActivityDTO);
        patientActivity = patientActivityRepository.save(patientActivity);
        return patientActivityMapper.toDto(patientActivity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PatientActivityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PatientActivities");
        return patientActivityRepository.findAll(pageable)
            .map(patientActivityMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<PatientActivityDTO> findOne(Long id) {
        log.debug("Request to get PatientActivity : {}", id);
        return patientActivityRepository.findById(id)
            .map(patientActivityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PatientActivity : {}", id);
        patientActivityRepository.deleteById(id);
    }
}
