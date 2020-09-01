package com.gok.ticketingbatch.repository;

import com.gok.ticketingbatch.domain.PatientActivity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PatientActivity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatientActivityRepository extends JpaRepository<PatientActivity, Long> {
}
