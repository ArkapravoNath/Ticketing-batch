package com.gok.ticketingbatch.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class MobileDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MobileDTO.class);
        MobileDTO mobileDTO1 = new MobileDTO();
        mobileDTO1.setId(1L);
        MobileDTO mobileDTO2 = new MobileDTO();
        assertThat(mobileDTO1).isNotEqualTo(mobileDTO2);
        mobileDTO2.setId(mobileDTO1.getId());
        assertThat(mobileDTO1).isEqualTo(mobileDTO2);
        mobileDTO2.setId(2L);
        assertThat(mobileDTO1).isNotEqualTo(mobileDTO2);
        mobileDTO1.setId(null);
        assertThat(mobileDTO1).isNotEqualTo(mobileDTO2);
    }
}
