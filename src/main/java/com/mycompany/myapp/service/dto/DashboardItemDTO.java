package com.mycompany.myapp.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DashboardItem} entity.
 */
@Schema(description = "not an ignored comment")
public class DashboardItemDTO implements Serializable {

    private String id;

    private Integer cols;

    private Integer rows;

    private Integer x;

    private Integer y;

    private String content;

    private String city;

    private String stateProvince;

    private UserProfileDTO userProfile;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
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
        if (!(o instanceof DashboardItemDTO)) {
            return false;
        }

        DashboardItemDTO dashboardItemDTO = (DashboardItemDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dashboardItemDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardItemDTO{" +
            "id='" + getId() + "'" +
            ", cols=" + getCols() +
            ", rows=" + getRows() +
            ", x=" + getX() +
            ", y=" + getY() +
            ", content='" + getContent() + "'" +
            ", city='" + getCity() + "'" +
            ", stateProvince='" + getStateProvince() + "'" +
            ", userProfile=" + getUserProfile() +
            "}";
    }
}
