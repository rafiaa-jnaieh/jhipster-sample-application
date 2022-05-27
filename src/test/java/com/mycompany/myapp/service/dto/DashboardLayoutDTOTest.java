package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DashboardLayoutDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DashboardLayoutDTO.class);
        DashboardLayoutDTO dashboardLayoutDTO1 = new DashboardLayoutDTO();
        dashboardLayoutDTO1.setId("id1");
        DashboardLayoutDTO dashboardLayoutDTO2 = new DashboardLayoutDTO();
        assertThat(dashboardLayoutDTO1).isNotEqualTo(dashboardLayoutDTO2);
        dashboardLayoutDTO2.setId(dashboardLayoutDTO1.getId());
        assertThat(dashboardLayoutDTO1).isEqualTo(dashboardLayoutDTO2);
        dashboardLayoutDTO2.setId("id2");
        assertThat(dashboardLayoutDTO1).isNotEqualTo(dashboardLayoutDTO2);
        dashboardLayoutDTO1.setId(null);
        assertThat(dashboardLayoutDTO1).isNotEqualTo(dashboardLayoutDTO2);
    }
}
