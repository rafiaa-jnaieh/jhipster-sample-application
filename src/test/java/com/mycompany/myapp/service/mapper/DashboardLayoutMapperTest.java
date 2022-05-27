package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DashboardLayoutMapperTest {

    private DashboardLayoutMapper dashboardLayoutMapper;

    @BeforeEach
    public void setUp() {
        dashboardLayoutMapper = new DashboardLayoutMapperImpl();
    }
}
