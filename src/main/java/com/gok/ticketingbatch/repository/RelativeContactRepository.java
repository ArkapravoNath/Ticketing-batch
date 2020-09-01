package com.gok.ticketingbatch.repository;

import com.gok.ticketingbatch.domain.RelativeContact;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the RelativeContact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RelativeContactRepository extends JpaRepository<RelativeContact, Long> {
}
