package com.gok.ticketingbatch.service.impl;

import com.gok.ticketingbatch.service.MedicalHistoryService;
import com.gok.ticketingbatch.domain.MedicalHistory;
import com.gok.ticketingbatch.repository.MedicalHistoryRepository;
import com.gok.ticketingbatch.service.dto.MedicalHistoryDTO;
import com.gok.ticketingbatch.service.mapper.MedicalHistoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalHistory}.
 */
@Service
@Transactional
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final Logger log = LoggerFactory.getLogger(MedicalHistoryServiceImpl.class);

    private final MedicalHistoryRepository medicalHistoryRepository;

    private final MedicalHistoryMapper medicalHistoryMapper;

    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository, MedicalHistoryMapper medicalHistoryMapper) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.medicalHistoryMapper = medicalHistoryMapper;
    }

    @Override
    public MedicalHistoryDTO save(MedicalHistoryDTO medicalHistoryDTO) {
        log.debug("Request to save MedicalHistory : {}", medicalHistoryDTO);
        MedicalHistory medicalHistory = medicalHistoryMapper.toEntity(medicalHistoryDTO);
        medicalHistory = medicalHistoryRepository.save(medicalHistory);
        return medicalHistoryMapper.toDto(medicalHistory);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MedicalHistoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalHistories");
        return medicalHistoryRepository.findAll(pageable)
            .map(medicalHistoryMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MedicalHistoryDTO> findOne(Long id) {
        log.debug("Request to get MedicalHistory : {}", id);
        return medicalHistoryRepository.findById(id)
            .map(medicalHistoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicalHistory : {}", id);
        medicalHistoryRepository.deleteById(id);
    }
}
