package com.gok.ticketingbatch.repository;

import com.gok.ticketingbatch.domain.KsrsacAddress;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KsrsacAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KsrsacAddressRepository extends JpaRepository<KsrsacAddress, Long> {
}
