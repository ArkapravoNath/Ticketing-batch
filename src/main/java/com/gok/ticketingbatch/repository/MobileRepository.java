package com.gok.ticketingbatch.repository;

import com.gok.ticketingbatch.domain.Mobile;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Mobile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MobileRepository extends JpaRepository<Mobile, Long> {
}
