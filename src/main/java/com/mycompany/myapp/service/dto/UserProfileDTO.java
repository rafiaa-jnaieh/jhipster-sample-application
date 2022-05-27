package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.UserProfile} entity.
 */
public class UserProfileDTO implements Serializable {

    private String id;

    private String name;

    private Integer priority;

    private DashboardLayoutDTO dashboardLayout;

    private DashboardConfigDTO dashboardConfig;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public DashboardLayoutDTO getDashboardLayout() {
        return dashboardLayout;
    }

    public void setDashboardLayout(DashboardLayoutDTO dashboardLayout) {
        this.dashboardLayout = dashboardLayout;
    }

    public DashboardConfigDTO getDashboardConfig() {
        return dashboardConfig;
    }

    public void setDashboardConfig(DashboardConfigDTO dashboardConfig) {
        this.dashboardConfig = dashboardConfig;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserProfileDTO)) {
            return false;
        }

        UserProfileDTO userProfileDTO = (UserProfileDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userProfileDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserProfileDTO{" +
            "id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", priority=" + getPriority() +
            ", dashboardLayout=" + getDashboardLayout() +
            ", dashboardConfig=" + getDashboardConfig() +
            "}";
    }
}
