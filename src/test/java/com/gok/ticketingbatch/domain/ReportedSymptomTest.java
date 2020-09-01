package com.gok.ticketingbatch.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.gok.ticketingbatch.web.rest.TestUtil;

public class ReportedSymptomTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportedSymptom.class);
        ReportedSymptom reportedSymptom1 = new ReportedSymptom();
        reportedSymptom1.setId(1L);
        ReportedSymptom reportedSymptom2 = new ReportedSymptom();
        reportedSymptom2.setId(reportedSymptom1.getId());
        assertThat(reportedSymptom1).isEqualTo(reportedSymptom2);
        reportedSymptom2.setId(2L);
        assertThat(reportedSymptom1).isNotEqualTo(reportedSymptom2);
        reportedSymptom1.setId(null);
        assertThat(reportedSymptom1).isNotEqualTo(reportedSymptom2);
    }
}
