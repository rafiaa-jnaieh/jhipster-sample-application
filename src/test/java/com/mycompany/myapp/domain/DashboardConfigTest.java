package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DashboardConfigTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DashboardConfig.class);
        DashboardConfig dashboardConfig1 = new DashboardConfig();
        dashboardConfig1.setId("id1");
        DashboardConfig dashboardConfig2 = new DashboardConfig();
        dashboardConfig2.setId(dashboardConfig1.getId());
        assertThat(dashboardConfig1).isEqualTo(dashboardConfig2);
        dashboardConfig2.setId("id2");
        assertThat(dashboardConfig1).isNotEqualTo(dashboardConfig2);
        dashboardConfig1.setId(null);
        assertThat(dashboardConfig1).isNotEqualTo(dashboardConfig2);
    }
}
