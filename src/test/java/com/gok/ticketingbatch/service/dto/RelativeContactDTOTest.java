package com.gok.ticketingbatch.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class RelativeContactDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelativeContactDTO.class);
        RelativeContactDTO relativeContactDTO1 = new RelativeContactDTO();
        relativeContactDTO1.setId(1L);
        RelativeContactDTO relativeContactDTO2 = new RelativeContactDTO();
        assertThat(relativeContactDTO1).isNotEqualTo(relativeContactDTO2);
        relativeContactDTO2.setId(relativeContactDTO1.getId());
        assertThat(relativeContactDTO1).isEqualTo(relativeContactDTO2);
        relativeContactDTO2.setId(2L);
        assertThat(relativeContactDTO1).isNotEqualTo(relativeContactDTO2);
        relativeContactDTO1.setId(null);
        assertThat(relativeContactDTO1).isNotEqualTo(relativeContactDTO2);
    }
}
