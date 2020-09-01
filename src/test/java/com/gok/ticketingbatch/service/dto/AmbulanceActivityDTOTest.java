package com.gok.ticketingbatch.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class AmbulanceActivityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AmbulanceActivityDTO.class);
        AmbulanceActivityDTO ambulanceActivityDTO1 = new AmbulanceActivityDTO();
        ambulanceActivityDTO1.setId(1L);
        AmbulanceActivityDTO ambulanceActivityDTO2 = new AmbulanceActivityDTO();
        assertThat(ambulanceActivityDTO1).isNotEqualTo(ambulanceActivityDTO2);
        ambulanceActivityDTO2.setId(ambulanceActivityDTO1.getId());
        assertThat(ambulanceActivityDTO1).isEqualTo(ambulanceActivityDTO2);
        ambulanceActivityDTO2.setId(2L);
        assertThat(ambulanceActivityDTO1).isNotEqualTo(ambulanceActivityDTO2);
        ambulanceActivityDTO1.setId(null);
        assertThat(ambulanceActivityDTO1).isNotEqualTo(ambulanceActivityDTO2);
    }
}
