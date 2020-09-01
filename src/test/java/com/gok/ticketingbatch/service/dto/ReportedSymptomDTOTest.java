package com.gok.ticketingbatch.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class ReportedSymptomDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportedSymptomDTO.class);
        ReportedSymptomDTO reportedSymptomDTO1 = new ReportedSymptomDTO();
        reportedSymptomDTO1.setId(1L);
        ReportedSymptomDTO reportedSymptomDTO2 = new ReportedSymptomDTO();
        assertThat(reportedSymptomDTO1).isNotEqualTo(reportedSymptomDTO2);
        reportedSymptomDTO2.setId(reportedSymptomDTO1.getId());
        assertThat(reportedSymptomDTO1).isEqualTo(reportedSymptomDTO2);
        reportedSymptomDTO2.setId(2L);
        assertThat(reportedSymptomDTO1).isNotEqualTo(reportedSymptomDTO2);
        reportedSymptomDTO1.setId(null);
        assertThat(reportedSymptomDTO1).isNotEqualTo(reportedSymptomDTO2);
    }
}
