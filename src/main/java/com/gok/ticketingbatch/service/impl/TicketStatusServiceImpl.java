package com.gok.ticketingbatch.service.impl;

import com.gok.ticketingbatch.service.TicketStatusService;
import com.gok.ticketingbatch.domain.TicketStatus;
import com.gok.ticketingbatch.repository.TicketStatusRepository;
import com.gok.ticketingbatch.service.dto.TicketStatusDTO;
import com.gok.ticketingbatch.service.mapper.TicketStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TicketStatus}.
 */
@Service
@Transactional
public class TicketStatusServiceImpl implements TicketStatusService {

    private final Logger log = LoggerFactory.getLogger(TicketStatusServiceImpl.class);

    private final TicketStatusRepository ticketStatusRepository;

    private final TicketStatusMapper ticketStatusMapper;

    public TicketStatusServiceImpl(TicketStatusRepository ticketStatusRepository, TicketStatusMapper ticketStatusMapper) {
        this.ticketStatusRepository = ticketStatusRepository;
        this.ticketStatusMapper = ticketStatusMapper;
    }

    @Override
    public TicketStatusDTO save(TicketStatusDTO ticketStatusDTO) {
        log.debug("Request to save TicketStatus : {}", ticketStatusDTO);
        TicketStatus ticketStatus = ticketStatusMapper.toEntity(ticketStatusDTO);
        ticketStatus = ticketStatusRepository.save(ticketStatus);
        return ticketStatusMapper.toDto(ticketStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TicketStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TicketStatuses");
        return ticketStatusRepository.findAll(pageable)
            .map(ticketStatusMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TicketStatusDTO> findOne(Long id) {
        log.debug("Request to get TicketStatus : {}", id);
        return ticketStatusRepository.findById(id)
            .map(ticketStatusMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TicketStatus : {}", id);
        ticketStatusRepository.deleteById(id);
    }
}
