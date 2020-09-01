package com.gok.ticketingbatch.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportedSymptomMapperTest {

    private ReportedSymptomMapper reportedSymptomMapper;

    @BeforeEach
    public void setUp() {
        reportedSymptomMapper = new ReportedSymptomMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(reportedSymptomMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(reportedSymptomMapper.fromId(null)).isNull();
    }
}
