package com.gok.ticketingbatch.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AmbulanceActivityMapperTest {

    private AmbulanceActivityMapper ambulanceActivityMapper;

    @BeforeEach
    public void setUp() {
        ambulanceActivityMapper = new AmbulanceActivityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ambulanceActivityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ambulanceActivityMapper.fromId(null)).isNull();
    }
}
