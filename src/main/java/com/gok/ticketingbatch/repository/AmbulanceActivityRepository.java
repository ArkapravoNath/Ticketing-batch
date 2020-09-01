package com.gok.ticketingbatch.repository;

import com.gok.ticketingbatch.domain.AmbulanceActivity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AmbulanceActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AmbulanceActivityRepository extends JpaRepository<AmbulanceActivity, Long> {
}
