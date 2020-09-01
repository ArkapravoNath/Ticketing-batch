package com.gok.ticketingbatch.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MobileMapperTest {

    private MobileMapper mobileMapper;

    @BeforeEach
    public void setUp() {
        mobileMapper = new MobileMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mobileMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mobileMapper.fromId(null)).isNull();
    }
}
