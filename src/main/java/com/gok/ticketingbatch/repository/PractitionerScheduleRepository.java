package com.gok.ticketingbatch.repository;

import com.gok.ticketingbatch.domain.PractitionerSchedule;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PractitionerSchedule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PractitionerScheduleRepository extends JpaRepository<PractitionerSchedule, Long> {
}
