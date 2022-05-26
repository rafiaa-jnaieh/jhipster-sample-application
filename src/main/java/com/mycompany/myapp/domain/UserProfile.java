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
    @Field("dashboardLayout")
    @JsonIgnoreProperties(value = { "userProfile" }, allowSetters = true)
    private Set<DashboardLayout> dashboardLayouts = new HashSet<>();

    @DBRef
    @Field("dashboardItem")
    @JsonIgnoreProperties(value = { "userProfile" }, allowSetters = true)
    private Set<DashboardItem> dashboardItems = new HashSet<>();

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

    public Set<DashboardLayout> getDashboardLayouts() {
        return this.dashboardLayouts;
    }

    public void setDashboardLayouts(Set<DashboardLayout> dashboardLayouts) {
        if (this.dashboardLayouts != null) {
            this.dashboardLayouts.forEach(i -> i.setUserProfile(null));
        }
        if (dashboardLayouts != null) {
            dashboardLayouts.forEach(i -> i.setUserProfile(this));
        }
        this.dashboardLayouts = dashboardLayouts;
    }

    public UserProfile dashboardLayouts(Set<DashboardLayout> dashboardLayouts) {
        this.setDashboardLayouts(dashboardLayouts);
        return this;
    }

    public UserProfile addDashboardLayout(DashboardLayout dashboardLayout) {
        this.dashboardLayouts.add(dashboardLayout);
        dashboardLayout.setUserProfile(this);
        return this;
    }

    public UserProfile removeDashboardLayout(DashboardLayout dashboardLayout) {
        this.dashboardLayouts.remove(dashboardLayout);
        dashboardLayout.setUserProfile(null);
        return this;
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
