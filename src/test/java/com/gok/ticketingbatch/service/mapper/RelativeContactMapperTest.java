package com.gok.ticketingbatch.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RelativeContactMapperTest {

    private RelativeContactMapper relativeContactMapper;

    @BeforeEach
    public void setUp() {
        relativeContactMapper = new RelativeContactMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(relativeContactMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(relativeContactMapper.fromId(null)).isNull();
    }
}
