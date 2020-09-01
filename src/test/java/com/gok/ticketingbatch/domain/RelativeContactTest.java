package com.gok.ticketingbatch.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class RelativeContactTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RelativeContact.class);
        RelativeContact relativeContact1 = new RelativeContact();
        relativeContact1.setId(1L);
        RelativeContact relativeContact2 = new RelativeContact();
        relativeContact2.setId(relativeContact1.getId());
        assertThat(relativeContact1).isEqualTo(relativeContact2);
        relativeContact2.setId(2L);
        assertThat(relativeContact1).isNotEqualTo(relativeContact2);
        relativeContact1.setId(null);
        assertThat(relativeContact1).isNotEqualTo(relativeContact2);
    }
}
