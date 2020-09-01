package com.gok.ticketingbatch.service.impl;

import com.gok.ticketingbatch.service.ReportedSymptomService;
import com.gok.ticketingbatch.domain.ReportedSymptom;
import com.gok.ticketingbatch.repository.ReportedSymptomRepository;
import com.gok.ticketingbatch.service.dto.ReportedSymptomDTO;
import com.gok.ticketingbatch.service.mapper.ReportedSymptomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ReportedSymptom}.
 */
@Service
@Transactional
public class ReportedSymptomServiceImpl implements ReportedSymptomService {

    private final Logger log = LoggerFactory.getLogger(ReportedSymptomServiceImpl.class);

    private final ReportedSymptomRepository reportedSymptomRepository;

    private final ReportedSymptomMapper reportedSymptomMapper;

    public ReportedSymptomServiceImpl(ReportedSymptomRepository reportedSymptomRepository, ReportedSymptomMapper reportedSymptomMapper) {
        this.reportedSymptomRepository = reportedSymptomRepository;
        this.reportedSymptomMapper = reportedSymptomMapper;
    }

    @Override
    public ReportedSymptomDTO save(ReportedSymptomDTO reportedSymptomDTO) {
        log.debug("Request to save ReportedSymptom : {}", reportedSymptomDTO);
        ReportedSymptom reportedSymptom = reportedSymptomMapper.toEntity(reportedSymptomDTO);
        reportedSymptom = reportedSymptomRepository.save(reportedSymptom);
        return reportedSymptomMapper.toDto(reportedSymptom);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReportedSymptomDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReportedSymptoms");
        return reportedSymptomRepository.findAll(pageable)
            .map(reportedSymptomMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ReportedSymptomDTO> findOne(Long id) {
        log.debug("Request to get ReportedSymptom : {}", id);
        return reportedSymptomRepository.findById(id)
            .map(reportedSymptomMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportedSymptom : {}", id);
        reportedSymptomRepository.deleteById(id);
    }
}
