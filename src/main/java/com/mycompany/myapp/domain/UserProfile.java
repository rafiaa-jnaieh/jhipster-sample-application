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
 * A UserProfile.
 */
@Document(collection = "user_profile")
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("priority")
    private Integer priority;

    @DBRef
    @Field("dashboardItem")
    @JsonIgnoreProperties(value = { "userProfile" }, allowSetters = true)
    private Set<DashboardItem> dashboardItems = new HashSet<>();

    @DBRef
    @Field("dashboardLayout")
    @JsonIgnoreProperties(value = { "userProfiles" }, allowSetters = true)
    private DashboardLayout dashboardLayout;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public UserProfile id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public UserProfile name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public UserProfile priority(Integer priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Set<DashboardItem> getDashboardItems() {
        return this.dashboardItems;
    }

    public void setDashboardItems(Set<DashboardItem> dashboardItems) {
        if (this.dashboardItems != null) {
            this.dashboardItems.forEach(i -> i.setUserProfile(null));
        }
        if (dashboardItems != null) {
            dashboardItems.forEach(i -> i.setUserProfile(this));
        }
        this.dashboardItems = dashboardItems;
    }

    public UserProfile dashboardItems(Set<DashboardItem> dashboardItems) {
        this.setDashboardItems(dashboardItems);
        return this;
    }

    public UserProfile addDashboardItem(DashboardItem dashboardItem) {
        this.dashboardItems.add(dashboardItem);
        dashboardItem.setUserProfile(this);
        return this;
    }

    public UserProfile removeDashboardItem(DashboardItem dashboardItem) {
        this.dashboardItems.remove(dashboardItem);
        dashboardItem.setUserProfile(null);
        return this;
    }

    public DashboardLayout getDashboardLayout() {
        return this.dashboardLayout;
    }

    public void setDashboardLayout(DashboardLayout dashboardLayout) {
        this.dashboardLayout = dashboardLayout;
    }

    public UserProfile dashboardLayout(DashboardLayout dashboardLayout) {
        this.setDashboardLayout(dashboardLayout);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserProfile)) {
            return false;
        }
        return id != null && id.equals(((UserProfile) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserProfile{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", priority=" + getPriority() +
            "}";
    }
}
