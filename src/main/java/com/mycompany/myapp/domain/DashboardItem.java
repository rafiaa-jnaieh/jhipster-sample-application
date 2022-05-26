package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * not an ignored comment
 */
@Document(collection = "dashboard_item")
public class DashboardItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("cols")
    private Integer cols;

    @Field("rows")
    private Integer rows;

    @Field("x")
    private Integer x;

    @Field("y")
    private Integer y;

    @Field("content")
    private String content;

    @Field("city")
    private String city;

    @Field("state_province")
    private String stateProvince;

    @DBRef
    @Field("userProfile")
    @JsonIgnoreProperties(value = { "dashboardLayouts", "dashboardItems" }, allowSetters = true)
    private UserProfile userProfile;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DashboardItem id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCols() {
        return this.cols;
    }

    public DashboardItem cols(Integer cols) {
        this.setCols(cols);
        return this;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public Integer getRows() {
        return this.rows;
    }

    public DashboardItem rows(Integer rows) {
        this.setRows(rows);
        return this;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getX() {
        return this.x;
    }

    public DashboardItem x(Integer x) {
        this.setX(x);
        return this;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return this.y;
    }

    public DashboardItem y(Integer y) {
        this.setY(y);
        return this;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getContent() {
        return this.content;
    }

    public DashboardItem content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCity() {
        return this.city;
    }

    public DashboardItem city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateProvince() {
        return this.stateProvince;
    }

    public DashboardItem stateProvince(String stateProvince) {
        this.setStateProvince(stateProvince);
        return this;
    }

    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }

    public UserProfile getUserProfile() {
        return this.userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public DashboardItem userProfile(UserProfile userProfile) {
        this.setUserProfile(userProfile);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardItem)) {
            return false;
        }
        return id != null && id.equals(((DashboardItem) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardItem{" +
            "id=" + getId() +
            ", cols=" + getCols() +
            ", rows=" + getRows() +
            ", x=" + getX() +
            ", y=" + getY() +
            ", content='" + getContent() + "'" +
            ", city='" + getCity() + "'" +
            ", stateProvince='" + getStateProvince() + "'" +
            "}";
    }
}
