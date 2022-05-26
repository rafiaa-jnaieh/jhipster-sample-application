package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DashboardLayout} entity.
 */
public class DashboardLayoutDTO implements Serializable {

    private String id;

    private Integer minCols;

    private Integer minRows;

    private Integer maxRows;

    private UserProfileDTO userProfile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMinCols() {
        return minCols;
    }

    public void setMinCols(Integer minCols) {
        this.minCols = minCols;
    }

    public Integer getMinRows() {
        return minRows;
    }

    public void setMinRows(Integer minRows) {
        this.minRows = minRows;
    }

    public Integer getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(Integer maxRows) {
        this.maxRows = maxRows;
    }

    public UserProfileDTO getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileDTO userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardLayoutDTO)) {
            return false;
        }

        DashboardLayoutDTO dashboardLayoutDTO = (DashboardLayoutDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dashboardLayoutDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardLayoutDTO{" +
            "id='" + getId() + "'" +
            ", minCols=" + getMinCols() +
            ", minRows=" + getMinRows() +
            ", maxRows=" + getMaxRows() +
            ", userProfile=" + getUserProfile() +
            "}";
    }
}
