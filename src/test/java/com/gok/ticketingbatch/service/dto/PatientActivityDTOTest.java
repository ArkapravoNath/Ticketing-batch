package com.gok.ticketingbatch.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class PatientActivityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PatientActivityDTO.class);
        PatientActivityDTO patientActivityDTO1 = new PatientActivityDTO();
        patientActivityDTO1.setId(1L);
        PatientActivityDTO patientActivityDTO2 = new PatientActivityDTO();
        assertThat(patientActivityDTO1).isNotEqualTo(patientActivityDTO2);
        patientActivityDTO2.setId(patientActivityDTO1.getId());
        assertThat(patientActivityDTO1).isEqualTo(patientActivityDTO2);
        patientActivityDTO2.setId(2L);
        assertThat(patientActivityDTO1).isNotEqualTo(patientActivityDTO2);
        patientActivityDTO1.setId(null);
        assertThat(patientActivityDTO1).isNotEqualTo(patientActivityDTO2);
    }
}
