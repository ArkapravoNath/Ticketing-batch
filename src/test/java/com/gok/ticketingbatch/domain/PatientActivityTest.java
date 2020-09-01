package com.gok.ticketingbatch.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class PatientActivityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientActivity.class);
        PatientActivity patientActivity1 = new PatientActivity();
        patientActivity1.setId(1L);
        PatientActivity patientActivity2 = new PatientActivity();
        patientActivity2.setId(patientActivity1.getId());
        assertThat(patientActivity1).isEqualTo(patientActivity2);
        patientActivity2.setId(2L);
        assertThat(patientActivity1).isNotEqualTo(patientActivity2);
        patientActivity1.setId(null);
        assertThat(patientActivity1).isNotEqualTo(patientActivity2);
    }
}
