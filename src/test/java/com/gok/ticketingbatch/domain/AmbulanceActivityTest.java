package com.gok.ticketingbatch.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class AmbulanceActivityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AmbulanceActivity.class);
        AmbulanceActivity ambulanceActivity1 = new AmbulanceActivity();
        ambulanceActivity1.setId(1L);
        AmbulanceActivity ambulanceActivity2 = new AmbulanceActivity();
        ambulanceActivity2.setId(ambulanceActivity1.getId());
        assertThat(ambulanceActivity1).isEqualTo(ambulanceActivity2);
        ambulanceActivity2.setId(2L);
        assertThat(ambulanceActivity1).isNotEqualTo(ambulanceActivity2);
        ambulanceActivity1.setId(null);
        assertThat(ambulanceActivity1).isNotEqualTo(ambulanceActivity2);
    }
}
