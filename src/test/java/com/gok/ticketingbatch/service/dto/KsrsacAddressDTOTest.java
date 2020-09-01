package com.gok.ticketingbatch.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class KsrsacAddressDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KsrsacAddressDTO.class);
        KsrsacAddressDTO ksrsacAddressDTO1 = new KsrsacAddressDTO();
        ksrsacAddressDTO1.setId(1L);
        KsrsacAddressDTO ksrsacAddressDTO2 = new KsrsacAddressDTO();
        assertThat(ksrsacAddressDTO1).isNotEqualTo(ksrsacAddressDTO2);
        ksrsacAddressDTO2.setId(ksrsacAddressDTO1.getId());
        assertThat(ksrsacAddressDTO1).isEqualTo(ksrsacAddressDTO2);
        ksrsacAddressDTO2.setId(2L);
        assertThat(ksrsacAddressDTO1).isNotEqualTo(ksrsacAddressDTO2);
        ksrsacAddressDTO1.setId(null);
        assertThat(ksrsacAddressDTO1).isNotEqualTo(ksrsacAddressDTO2);
    }
}
