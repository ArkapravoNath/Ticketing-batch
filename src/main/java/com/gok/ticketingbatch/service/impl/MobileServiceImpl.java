package com.gok.ticketingbatch.service.impl;

import com.gok.ticketingbatch.service.MobileService;
import com.gok.ticketingbatch.domain.Mobile;
import com.gok.ticketingbatch.repository.MobileRepository;
import com.gok.ticketingbatch.service.dto.MobileDTO;
import com.gok.ticketingbatch.service.mapper.MobileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Mobile}.
 */
@Service
@Transactional
public class MobileServiceImpl implements MobileService {

    private final Logger log = LoggerFactory.getLogger(MobileServiceImpl.class);

    private final MobileRepository mobileRepository;

    private final MobileMapper mobileMapper;

    public MobileServiceImpl(MobileRepository mobileRepository, MobileMapper mobileMapper) {
        this.mobileRepository = mobileRepository;
        this.mobileMapper = mobileMapper;
    }

    @Override
    public MobileDTO save(MobileDTO mobileDTO) {
        log.debug("Request to save Mobile : {}", mobileDTO);
        Mobile mobile = mobileMapper.toEntity(mobileDTO);
        mobile = mobileRepository.save(mobile);
        return mobileMapper.toDto(mobile);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MobileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Mobiles");
        return mobileRepository.findAll(pageable)
            .map(mobileMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MobileDTO> findOne(Long id) {
        log.debug("Request to get Mobile : {}", id);
        return mobileRepository.findById(id)
            .map(mobileMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mobile : {}", id);
        mobileRepository.deleteById(id);
    }
}
