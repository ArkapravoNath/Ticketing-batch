package com.gok.ticketingbatch.service.impl;

import com.gok.ticketingbatch.service.DiagnosisService;
import com.gok.ticketingbatch.domain.Diagnosis;
import com.gok.ticketingbatch.repository.DiagnosisRepository;
import com.gok.ticketingbatch.service.dto.DiagnosisDTO;
import com.gok.ticketingbatch.service.mapper.DiagnosisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Diagnosis}.
 */
@Service
@Transactional
public class DiagnosisServiceImpl implements DiagnosisService {

    private final Logger log = LoggerFactory.getLogger(DiagnosisServiceImpl.class);

    private final DiagnosisRepository diagnosisRepository;

    private final DiagnosisMapper diagnosisMapper;

    public DiagnosisServiceImpl(DiagnosisRepository diagnosisRepository, DiagnosisMapper diagnosisMapper) {
        this.diagnosisRepository = diagnosisRepository;
        this.diagnosisMapper = diagnosisMapper;
    }

    @Override
    public DiagnosisDTO save(DiagnosisDTO diagnosisDTO) {
        log.debug("Request to save Diagnosis : {}", diagnosisDTO);
        Diagnosis diagnosis = diagnosisMapper.toEntity(diagnosisDTO);
        diagnosis = diagnosisRepository.save(diagnosis);
        return diagnosisMapper.toDto(diagnosis);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DiagnosisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Diagnoses");
        return diagnosisRepository.findAll(pageable)
            .map(diagnosisMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DiagnosisDTO> findOne(Long id) {
        log.debug("Request to get Diagnosis : {}", id);
        return diagnosisRepository.findById(id)
            .map(diagnosisMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Diagnosis : {}", id);
        diagnosisRepository.deleteById(id);
    }
}
