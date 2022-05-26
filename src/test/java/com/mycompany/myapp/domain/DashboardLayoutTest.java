package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DashboardLayoutTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DashboardLayout.class);
        DashboardLayout dashboardLayout1 = new DashboardLayout();
        dashboardLayout1.setId("id1");
        DashboardLayout dashboardLayout2 = new DashboardLayout();
        dashboardLayout2.setId(dashboardLayout1.getId());
        assertThat(dashboardLayout1).isEqualTo(dashboardLayout2);
        dashboardLayout2.setId("id2");
        assertThat(dashboardLayout1).isNotEqualTo(dashboardLayout2);
        dashboardLayout1.setId(null);
        assertThat(dashboardLayout1).isNotEqualTo(dashboardLayout2);
    }
}
