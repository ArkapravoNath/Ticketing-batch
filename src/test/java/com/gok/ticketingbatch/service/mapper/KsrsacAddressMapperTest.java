package com.gok.ticketingbatch.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class KsrsacAddressMapperTest {

    private KsrsacAddressMapper ksrsacAddressMapper;

    @BeforeEach
    public void setUp() {
        ksrsacAddressMapper = new KsrsacAddressMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(ksrsacAddressMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(ksrsacAddressMapper.fromId(null)).isNull();
    }
}
