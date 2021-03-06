package com.gok.ticketingbatch.repository;

import com.gok.ticketingbatch.domain.TicketAction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TicketAction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TicketActionRepository extends JpaRepository<TicketAction, Long> {
}
