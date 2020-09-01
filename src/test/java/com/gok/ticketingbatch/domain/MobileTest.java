package com.gok.ticketingbatch.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class MobileTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mobile.class);
        Mobile mobile1 = new Mobile();
        mobile1.setId(1L);
        Mobile mobile2 = new Mobile();
        mobile2.setId(mobile1.getId());
        assertThat(mobile1).isEqualTo(mobile2);
        mobile2.setId(2L);
        assertThat(mobile1).isNotEqualTo(mobile2);
        mobile1.setId(null);
        assertThat(mobile1).isNotEqualTo(mobile2);
    }
}
