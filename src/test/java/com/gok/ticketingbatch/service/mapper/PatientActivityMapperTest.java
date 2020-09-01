package com.gok.ticketingbatch.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PatientActivityMapperTest {

    private PatientActivityMapper patientActivityMapper;

    @BeforeEach
    public void setUp() {
        patientActivityMapper = new PatientActivityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(patientActivityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(patientActivityMapper.fromId(null)).isNull();
    }
}
