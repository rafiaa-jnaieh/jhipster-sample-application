package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DashboardConfigDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DashboardConfigDTO.class);
        DashboardConfigDTO dashboardConfigDTO1 = new DashboardConfigDTO();
        dashboardConfigDTO1.setId("id1");
        DashboardConfigDTO dashboardConfigDTO2 = new DashboardConfigDTO();
        assertThat(dashboardConfigDTO1).isNotEqualTo(dashboardConfigDTO2);
        dashboardConfigDTO2.setId(dashboardConfigDTO1.getId());
        assertThat(dashboardConfigDTO1).isEqualTo(dashboardConfigDTO2);
        dashboardConfigDTO2.setId("id2");
        assertThat(dashboardConfigDTO1).isNotEqualTo(dashboardConfigDTO2);
        dashboardConfigDTO1.setId(null);
        assertThat(dashboardConfigDTO1).isNotEqualTo(dashboardConfigDTO2);
    }
}
