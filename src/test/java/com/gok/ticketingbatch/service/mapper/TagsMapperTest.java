package com.gok.ticketingbatch.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TagsMapperTest {

    private TagsMapper tagsMapper;

    @BeforeEach
    public void setUp() {
        tagsMapper = new TagsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tagsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tagsMapper.fromId(null)).isNull();
    }
}
