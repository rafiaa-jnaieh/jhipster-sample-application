package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DashboardLayout.
 */
@Document(collection = "dashboard_layout")
public class DashboardLayout implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("min_cols")
    private Integer minCols;

    @Field("min_rows")
    private Integer minRows;

    @Field("max_rows")
    private Integer maxRows;

    @DBRef
    @Field("userProfile")
    @JsonIgnoreProperties(value = { "dashboardItems", "dashboardLayout" }, allowSetters = true)
    private Set<UserProfile> userProfiles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DashboardLayout id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMinCols() {
        return this.minCols;
    }

    public DashboardLayout minCols(Integer minCols) {
        this.setMinCols(minCols);
        return this;
    }

    public void setMinCols(Integer minCols) {
        this.minCols = minCols;
    }

    public Integer getMinRows() {
        return this.minRows;
    }

    public DashboardLayout minRows(Integer minRows) {
        this.setMinRows(minRows);
        return this;
    }

    public void setMinRows(Integer minRows) {
        this.minRows = minRows;
    }

    public Integer getMaxRows() {
        return this.maxRows;
    }

    public DashboardLayout maxRows(Integer maxRows) {
        this.setMaxRows(maxRows);
        return this;
    }

    public void setMaxRows(Integer maxRows) {
        this.maxRows = maxRows;
    }

    public Set<UserProfile> getUserProfiles() {
        return this.userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        if (this.userProfiles != null) {
            this.userProfiles.forEach(i -> i.setDashboardLayout(null));
        }
        if (userProfiles != null) {
            userProfiles.forEach(i -> i.setDashboardLayout(this));
        }
        this.userProfiles = userProfiles;
    }

    public DashboardLayout userProfiles(Set<UserProfile> userProfiles) {
        this.setUserProfiles(userProfiles);
        return this;
    }

    public DashboardLayout addUserProfile(UserProfile userProfile) {
        this.userProfiles.add(userProfile);
        userProfile.setDashboardLayout(this);
        return this;
    }

    public DashboardLayout removeUserProfile(UserProfile userProfile) {
        this.userProfiles.remove(userProfile);
        userProfile.setDashboardLayout(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardLayout)) {
            return false;
        }
        return id != null && id.equals(((DashboardLayout) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardLayout{" +
            "id=" + getId() +
            ", minCols=" + getMinCols() +
            ", minRows=" + getMinRows() +
            ", maxRows=" + getMaxRows() +
            "}";
    }
}
