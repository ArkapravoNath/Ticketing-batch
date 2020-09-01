package com.gok.ticketingbatch.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class KsrsacAddressTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(KsrsacAddress.class);
        KsrsacAddress ksrsacAddress1 = new KsrsacAddress();
        ksrsacAddress1.setId(1L);
        KsrsacAddress ksrsacAddress2 = new KsrsacAddress();
        ksrsacAddress2.setId(ksrsacAddress1.getId());
        assertThat(ksrsacAddress1).isEqualTo(ksrsacAddress2);
        ksrsacAddress2.setId(2L);
        assertThat(ksrsacAddress1).isNotEqualTo(ksrsacAddress2);
        ksrsacAddress1.setId(null);
        assertThat(ksrsacAddress1).isNotEqualTo(ksrsacAddress2);
    }
}
