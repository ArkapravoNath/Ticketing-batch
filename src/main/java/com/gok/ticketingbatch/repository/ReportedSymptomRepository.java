package com.gok.ticketingbatch.repository;

import com.gok.ticketingbatch.domain.ReportedSymptom;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ReportedSymptom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportedSymptomRepository extends JpaRepository<ReportedSymptom, Long> {
}
