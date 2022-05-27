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
 * A DashboardConfig.
 */
@Document(collection = "dashboard_config")
public class DashboardConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("margin")
    private Integer margin;

    @Field("outer_margin")
    private Boolean outerMargin;

    @Field("outer_margin_top")
    private Integer outerMarginTop;

    @Field("outer_margin_right")
    private Integer outerMarginRight;

    @Field("outer_margin_bottom")
    private Integer outerMarginBottom;

    @Field("outer_margin_left")
    private Integer outerMarginLeft;

    @Field("use_transform_positioning")
    private Boolean useTransformPositioning;

    @Field("mobile_breakpoint")
    private Integer mobileBreakpoint;

    @Field("use_body_for_breakpoint")
    private Boolean useBodyForBreakpoint;

    @Field("min_cols")
    private Integer minCols;

    @Field("max_cols")
    private Integer maxCols;

    @Field("min_rows")
    private Integer minRows;

    @Field("max_rows")
    private Integer maxRows;

    @Field("max_item_cols")
    private Integer maxItemCols;

    @Field("min_item_cols")
    private Integer minItemCols;

    @Field("max_item_rows")
    private Integer maxItemRows;

    @Field("min_item_rows")
    private Integer minItemRows;

    @Field("max_item_area")
    private Integer maxItemArea;

    @Field("min_item_area")
    private Integer minItemArea;

    @Field("default_item_cols")
    private Integer defaultItemCols;

    @Field("default_item_rows")
    private Integer defaultItemRows;

    @Field("fixed_col_width")
    private Integer fixedColWidth;

    @Field("fixed_row_height")
    private Integer fixedRowHeight;

    @Field("keep_fixed_height_in_mobile")
    private Boolean keepFixedHeightInMobile;

    @Field("keep_fixed_width_in_mobile")
    private Boolean keepFixedWidthInMobile;

    @Field("scroll_sensitivity")
    private Integer scrollSensitivity;

    @Field("scroll_speed")
    private Integer scrollSpeed;

    @Field("enable_empty_cell_click")
    private Boolean enableEmptyCellClick;

    @Field("enable_empty_cell_context_menu")
    private Boolean enableEmptyCellContextMenu;

    @Field("enable_empty_cell_drop")
    private Boolean enableEmptyCellDrop;

    @Field("enable_empty_cell_drag")
    private Boolean enableEmptyCellDrag;

    @Field("enable_occupied_cell_drop")
    private Boolean enableOccupiedCellDrop;

    @Field("empty_cell_drag_max_cols")
    private Integer emptyCellDragMaxCols;

    @Field("empty_cell_drag_max_rows")
    private Integer emptyCellDragMaxRows;

    @Field("ignore_margin_in_row")
    private Boolean ignoreMarginInRow;

    @Field("draggable")
    private Boolean draggable;

    @Field("resizable")
    private Boolean resizable;

    @Field("swap")
    private Boolean swap;

    @Field("push_items")
    private Boolean pushItems;

    @Field("disable_push_on_drag")
    private Boolean disablePushOnDrag;

    @Field("disable_push_on_resize")
    private Boolean disablePushOnResize;

    @Field("push_resize_items")
    private Boolean pushResizeItems;

    @Field("disable_window_resize")
    private Boolean disableWindowResize;

    @Field("disable_warnings")
    private Boolean disableWarnings;

    @Field("scroll_to_new_items")
    private Boolean scrollToNewItems;

    @DBRef
    @Field("userProfile")
    @JsonIgnoreProperties(value = { "dashboardItems", "dashboardLayout", "dashboardConfig" }, allowSetters = true)
    private Set<UserProfile> userProfiles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DashboardConfig id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMargin() {
        return this.margin;
    }

    public DashboardConfig margin(Integer margin) {
        this.setMargin(margin);
        return this;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Boolean getOuterMargin() {
        return this.outerMargin;
    }

    public DashboardConfig outerMargin(Boolean outerMargin) {
        this.setOuterMargin(outerMargin);
        return this;
    }

    public void setOuterMargin(Boolean outerMargin) {
        this.outerMargin = outerMargin;
    }

    public Integer getOuterMarginTop() {
        return this.outerMarginTop;
    }

    public DashboardConfig outerMarginTop(Integer outerMarginTop) {
        this.setOuterMarginTop(outerMarginTop);
        return this;
    }

    public void setOuterMarginTop(Integer outerMarginTop) {
        this.outerMarginTop = outerMarginTop;
    }

    public Integer getOuterMarginRight() {
        return this.outerMarginRight;
    }

    public DashboardConfig outerMarginRight(Integer outerMarginRight) {
        this.setOuterMarginRight(outerMarginRight);
        return this;
    }

    public void setOuterMarginRight(Integer outerMarginRight) {
        this.outerMarginRight = outerMarginRight;
    }

    public Integer getOuterMarginBottom() {
        return this.outerMarginBottom;
    }

    public DashboardConfig outerMarginBottom(Integer outerMarginBottom) {
        this.setOuterMarginBottom(outerMarginBottom);
        return this;
    }

    public void setOuterMarginBottom(Integer outerMarginBottom) {
        this.outerMarginBottom = outerMarginBottom;
    }

    public Integer getOuterMarginLeft() {
        return this.outerMarginLeft;
    }

    public DashboardConfig outerMarginLeft(Integer outerMarginLeft) {
        this.setOuterMarginLeft(outerMarginLeft);
        return this;
    }

    public void setOuterMarginLeft(Integer outerMarginLeft) {
        this.outerMarginLeft = outerMarginLeft;
    }

    public Boolean getUseTransformPositioning() {
        return this.useTransformPositioning;
    }

    public DashboardConfig useTransformPositioning(Boolean useTransformPositioning) {
        this.setUseTransformPositioning(useTransformPositioning);
        return this;
    }

    public void setUseTransformPositioning(Boolean useTransformPositioning) {
        this.useTransformPositioning = useTransformPositioning;
    }

    public Integer getMobileBreakpoint() {
        return this.mobileBreakpoint;
    }

    public DashboardConfig mobileBreakpoint(Integer mobileBreakpoint) {
        this.setMobileBreakpoint(mobileBreakpoint);
        return this;
    }

    public void setMobileBreakpoint(Integer mobileBreakpoint) {
        this.mobileBreakpoint = mobileBreakpoint;
    }

    public Boolean getUseBodyForBreakpoint() {
        return this.useBodyForBreakpoint;
    }

    public DashboardConfig useBodyForBreakpoint(Boolean useBodyForBreakpoint) {
        this.setUseBodyForBreakpoint(useBodyForBreakpoint);
        return this;
    }

    public void setUseBodyForBreakpoint(Boolean useBodyForBreakpoint) {
        this.useBodyForBreakpoint = useBodyForBreakpoint;
    }

    public Integer getMinCols() {
        return this.minCols;
    }

    public DashboardConfig minCols(Integer minCols) {
        this.setMinCols(minCols);
        return this;
    }

    public void setMinCols(Integer minCols) {
        this.minCols = minCols;
    }

    public Integer getMaxCols() {
        return this.maxCols;
    }

    public DashboardConfig maxCols(Integer maxCols) {
        this.setMaxCols(maxCols);
        return this;
    }

    public void setMaxCols(Integer maxCols) {
        this.maxCols = maxCols;
    }

    public Integer getMinRows() {
        return this.minRows;
    }

    public DashboardConfig minRows(Integer minRows) {
        this.setMinRows(minRows);
        return this;
    }

    public void setMinRows(Integer minRows) {
        this.minRows = minRows;
    }

    public Integer getMaxRows() {
        return this.maxRows;
    }

    public DashboardConfig maxRows(Integer maxRows) {
        this.setMaxRows(maxRows);
        return this;
    }

    public void setMaxRows(Integer maxRows) {
        this.maxRows = maxRows;
    }

    public Integer getMaxItemCols() {
        return this.maxItemCols;
    }

    public DashboardConfig maxItemCols(Integer maxItemCols) {
        this.setMaxItemCols(maxItemCols);
        return this;
    }

    public void setMaxItemCols(Integer maxItemCols) {
        this.maxItemCols = maxItemCols;
    }

    public Integer getMinItemCols() {
        return this.minItemCols;
    }

    public DashboardConfig minItemCols(Integer minItemCols) {
        this.setMinItemCols(minItemCols);
        return this;
    }

    public void setMinItemCols(Integer minItemCols) {
        this.minItemCols = minItemCols;
    }

    public Integer getMaxItemRows() {
        return this.maxItemRows;
    }

    public DashboardConfig maxItemRows(Integer maxItemRows) {
        this.setMaxItemRows(maxItemRows);
        return this;
    }

    public void setMaxItemRows(Integer maxItemRows) {
        this.maxItemRows = maxItemRows;
    }

    public Integer getMinItemRows() {
        return this.minItemRows;
    }

    public DashboardConfig minItemRows(Integer minItemRows) {
        this.setMinItemRows(minItemRows);
        return this;
    }

    public void setMinItemRows(Integer minItemRows) {
        this.minItemRows = minItemRows;
    }

    public Integer getMaxItemArea() {
        return this.maxItemArea;
    }

    public DashboardConfig maxItemArea(Integer maxItemArea) {
        this.setMaxItemArea(maxItemArea);
        return this;
    }

    public void setMaxItemArea(Integer maxItemArea) {
        this.maxItemArea = maxItemArea;
    }

    public Integer getMinItemArea() {
        return this.minItemArea;
    }

    public DashboardConfig minItemArea(Integer minItemArea) {
        this.setMinItemArea(minItemArea);
        return this;
    }

    public void setMinItemArea(Integer minItemArea) {
        this.minItemArea = minItemArea;
    }

    public Integer getDefaultItemCols() {
        return this.defaultItemCols;
    }

    public DashboardConfig defaultItemCols(Integer defaultItemCols) {
        this.setDefaultItemCols(defaultItemCols);
        return this;
    }

    public void setDefaultItemCols(Integer defaultItemCols) {
        this.defaultItemCols = defaultItemCols;
    }

    public Integer getDefaultItemRows() {
        return this.defaultItemRows;
    }

    public DashboardConfig defaultItemRows(Integer defaultItemRows) {
        this.setDefaultItemRows(defaultItemRows);
        return this;
    }

    public void setDefaultItemRows(Integer defaultItemRows) {
        this.defaultItemRows = defaultItemRows;
    }

    public Integer getFixedColWidth() {
        return this.fixedColWidth;
    }

    public DashboardConfig fixedColWidth(Integer fixedColWidth) {
        this.setFixedColWidth(fixedColWidth);
        return this;
    }

    public void setFixedColWidth(Integer fixedColWidth) {
        this.fixedColWidth = fixedColWidth;
    }

    public Integer getFixedRowHeight() {
        return this.fixedRowHeight;
    }

    public DashboardConfig fixedRowHeight(Integer fixedRowHeight) {
        this.setFixedRowHeight(fixedRowHeight);
        return this;
    }

    public void setFixedRowHeight(Integer fixedRowHeight) {
        this.fixedRowHeight = fixedRowHeight;
    }

    public Boolean getKeepFixedHeightInMobile() {
        return this.keepFixedHeightInMobile;
    }

    public DashboardConfig keepFixedHeightInMobile(Boolean keepFixedHeightInMobile) {
        this.setKeepFixedHeightInMobile(keepFixedHeightInMobile);
        return this;
    }

    public void setKeepFixedHeightInMobile(Boolean keepFixedHeightInMobile) {
        this.keepFixedHeightInMobile = keepFixedHeightInMobile;
    }

    public Boolean getKeepFixedWidthInMobile() {
        return this.keepFixedWidthInMobile;
    }

    public DashboardConfig keepFixedWidthInMobile(Boolean keepFixedWidthInMobile) {
        this.setKeepFixedWidthInMobile(keepFixedWidthInMobile);
        return this;
    }

    public void setKeepFixedWidthInMobile(Boolean keepFixedWidthInMobile) {
        this.keepFixedWidthInMobile = keepFixedWidthInMobile;
    }

    public Integer getScrollSensitivity() {
        return this.scrollSensitivity;
    }

    public DashboardConfig scrollSensitivity(Integer scrollSensitivity) {
        this.setScrollSensitivity(scrollSensitivity);
        return this;
    }

    public void setScrollSensitivity(Integer scrollSensitivity) {
        this.scrollSensitivity = scrollSensitivity;
    }

    public Integer getScrollSpeed() {
        return this.scrollSpeed;
    }

    public DashboardConfig scrollSpeed(Integer scrollSpeed) {
        this.setScrollSpeed(scrollSpeed);
        return this;
    }

    public void setScrollSpeed(Integer scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    public Boolean getEnableEmptyCellClick() {
        return this.enableEmptyCellClick;
    }

    public DashboardConfig enableEmptyCellClick(Boolean enableEmptyCellClick) {
        this.setEnableEmptyCellClick(enableEmptyCellClick);
        return this;
    }

    public void setEnableEmptyCellClick(Boolean enableEmptyCellClick) {
        this.enableEmptyCellClick = enableEmptyCellClick;
    }

    public Boolean getEnableEmptyCellContextMenu() {
        return this.enableEmptyCellContextMenu;
    }

    public DashboardConfig enableEmptyCellContextMenu(Boolean enableEmptyCellContextMenu) {
        this.setEnableEmptyCellContextMenu(enableEmptyCellContextMenu);
        return this;
    }

    public void setEnableEmptyCellContextMenu(Boolean enableEmptyCellContextMenu) {
        this.enableEmptyCellContextMenu = enableEmptyCellContextMenu;
    }

    public Boolean getEnableEmptyCellDrop() {
        return this.enableEmptyCellDrop;
    }

    public DashboardConfig enableEmptyCellDrop(Boolean enableEmptyCellDrop) {
        this.setEnableEmptyCellDrop(enableEmptyCellDrop);
        return this;
    }

    public void setEnableEmptyCellDrop(Boolean enableEmptyCellDrop) {
        this.enableEmptyCellDrop = enableEmptyCellDrop;
    }

    public Boolean getEnableEmptyCellDrag() {
        return this.enableEmptyCellDrag;
    }

    public DashboardConfig enableEmptyCellDrag(Boolean enableEmptyCellDrag) {
        this.setEnableEmptyCellDrag(enableEmptyCellDrag);
        return this;
    }

    public void setEnableEmptyCellDrag(Boolean enableEmptyCellDrag) {
        this.enableEmptyCellDrag = enableEmptyCellDrag;
    }

    public Boolean getEnableOccupiedCellDrop() {
        return this.enableOccupiedCellDrop;
    }

    public DashboardConfig enableOccupiedCellDrop(Boolean enableOccupiedCellDrop) {
        this.setEnableOccupiedCellDrop(enableOccupiedCellDrop);
        return this;
    }

    public void setEnableOccupiedCellDrop(Boolean enableOccupiedCellDrop) {
        this.enableOccupiedCellDrop = enableOccupiedCellDrop;
    }

    public Integer getEmptyCellDragMaxCols() {
        return this.emptyCellDragMaxCols;
    }

    public DashboardConfig emptyCellDragMaxCols(Integer emptyCellDragMaxCols) {
        this.setEmptyCellDragMaxCols(emptyCellDragMaxCols);
        return this;
    }

    public void setEmptyCellDragMaxCols(Integer emptyCellDragMaxCols) {
        this.emptyCellDragMaxCols = emptyCellDragMaxCols;
    }

    public Integer getEmptyCellDragMaxRows() {
        return this.emptyCellDragMaxRows;
    }

    public DashboardConfig emptyCellDragMaxRows(Integer emptyCellDragMaxRows) {
        this.setEmptyCellDragMaxRows(emptyCellDragMaxRows);
        return this;
    }

    public void setEmptyCellDragMaxRows(Integer emptyCellDragMaxRows) {
        this.emptyCellDragMaxRows = emptyCellDragMaxRows;
    }

    public Boolean getIgnoreMarginInRow() {
        return this.ignoreMarginInRow;
    }

    public DashboardConfig ignoreMarginInRow(Boolean ignoreMarginInRow) {
        this.setIgnoreMarginInRow(ignoreMarginInRow);
        return this;
    }

    public void setIgnoreMarginInRow(Boolean ignoreMarginInRow) {
        this.ignoreMarginInRow = ignoreMarginInRow;
    }

    public Boolean getDraggable() {
        return this.draggable;
    }

    public DashboardConfig draggable(Boolean draggable) {
        this.setDraggable(draggable);
        return this;
    }

    public void setDraggable(Boolean draggable) {
        this.draggable = draggable;
    }

    public Boolean getResizable() {
        return this.resizable;
    }

    public DashboardConfig resizable(Boolean resizable) {
        this.setResizable(resizable);
        return this;
    }

    public void setResizable(Boolean resizable) {
        this.resizable = resizable;
    }

    public Boolean getSwap() {
        return this.swap;
    }

    public DashboardConfig swap(Boolean swap) {
        this.setSwap(swap);
        return this;
    }

    public void setSwap(Boolean swap) {
        this.swap = swap;
    }

    public Boolean getPushItems() {
        return this.pushItems;
    }

    public DashboardConfig pushItems(Boolean pushItems) {
        this.setPushItems(pushItems);
        return this;
    }

    public void setPushItems(Boolean pushItems) {
        this.pushItems = pushItems;
    }

    public Boolean getDisablePushOnDrag() {
        return this.disablePushOnDrag;
    }

    public DashboardConfig disablePushOnDrag(Boolean disablePushOnDrag) {
        this.setDisablePushOnDrag(disablePushOnDrag);
        return this;
    }

    public void setDisablePushOnDrag(Boolean disablePushOnDrag) {
        this.disablePushOnDrag = disablePushOnDrag;
    }

    public Boolean getDisablePushOnResize() {
        return this.disablePushOnResize;
    }

    public DashboardConfig disablePushOnResize(Boolean disablePushOnResize) {
        this.setDisablePushOnResize(disablePushOnResize);
        return this;
    }

    public void setDisablePushOnResize(Boolean disablePushOnResize) {
        this.disablePushOnResize = disablePushOnResize;
    }

    public Boolean getPushResizeItems() {
        return this.pushResizeItems;
    }

    public DashboardConfig pushResizeItems(Boolean pushResizeItems) {
        this.setPushResizeItems(pushResizeItems);
        return this;
    }

    public void setPushResizeItems(Boolean pushResizeItems) {
        this.pushResizeItems = pushResizeItems;
    }

    public Boolean getDisableWindowResize() {
        return this.disableWindowResize;
    }

    public DashboardConfig disableWindowResize(Boolean disableWindowResize) {
        this.setDisableWindowResize(disableWindowResize);
        return this;
    }

    public void setDisableWindowResize(Boolean disableWindowResize) {
        this.disableWindowResize = disableWindowResize;
    }

    public Boolean getDisableWarnings() {
        return this.disableWarnings;
    }

    public DashboardConfig disableWarnings(Boolean disableWarnings) {
        this.setDisableWarnings(disableWarnings);
        return this;
    }

    public void setDisableWarnings(Boolean disableWarnings) {
        this.disableWarnings = disableWarnings;
    }

    public Boolean getScrollToNewItems() {
        return this.scrollToNewItems;
    }

    public DashboardConfig scrollToNewItems(Boolean scrollToNewItems) {
        this.setScrollToNewItems(scrollToNewItems);
        return this;
    }

    public void setScrollToNewItems(Boolean scrollToNewItems) {
        this.scrollToNewItems = scrollToNewItems;
    }

    public Set<UserProfile> getUserProfiles() {
        return this.userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> userProfiles) {
        if (this.userProfiles != null) {
            this.userProfiles.forEach(i -> i.setDashboardConfig(null));
        }
        if (userProfiles != null) {
            userProfiles.forEach(i -> i.setDashboardConfig(this));
        }
        this.userProfiles = userProfiles;
    }

    public DashboardConfig userProfiles(Set<UserProfile> userProfiles) {
        this.setUserProfiles(userProfiles);
        return this;
    }

    public DashboardConfig addUserProfile(UserProfile userProfile) {
        this.userProfiles.add(userProfile);
        userProfile.setDashboardConfig(this);
        return this;
    }

    public DashboardConfig removeUserProfile(UserProfile userProfile) {
        this.userProfiles.remove(userProfile);
        userProfile.setDashboardConfig(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardConfig)) {
            return false;
        }
        return id != null && id.equals(((DashboardConfig) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardConfig{" +
            "id=" + getId() +
            ", margin=" + getMargin() +
            ", outerMargin='" + getOuterMargin() + "'" +
            ", outerMarginTop=" + getOuterMarginTop() +
            ", outerMarginRight=" + getOuterMarginRight() +
            ", outerMarginBottom=" + getOuterMarginBottom() +
            ", outerMarginLeft=" + getOuterMarginLeft() +
            ", useTransformPositioning='" + getUseTransformPositioning() + "'" +
            ", mobileBreakpoint=" + getMobileBreakpoint() +
            ", useBodyForBreakpoint='" + getUseBodyForBreakpoint() + "'" +
            ", minCols=" + getMinCols() +
            ", maxCols=" + getMaxCols() +
            ", minRows=" + getMinRows() +
            ", maxRows=" + getMaxRows() +
            ", maxItemCols=" + getMaxItemCols() +
            ", minItemCols=" + getMinItemCols() +
            ", maxItemRows=" + getMaxItemRows() +
            ", minItemRows=" + getMinItemRows() +
            ", maxItemArea=" + getMaxItemArea() +
            ", minItemArea=" + getMinItemArea() +
            ", defaultItemCols=" + getDefaultItemCols() +
            ", defaultItemRows=" + getDefaultItemRows() +
            ", fixedColWidth=" + getFixedColWidth() +
            ", fixedRowHeight=" + getFixedRowHeight() +
            ", keepFixedHeightInMobile='" + getKeepFixedHeightInMobile() + "'" +
            ", keepFixedWidthInMobile='" + getKeepFixedWidthInMobile() + "'" +
            ", scrollSensitivity=" + getScrollSensitivity() +
            ", scrollSpeed=" + getScrollSpeed() +
            ", enableEmptyCellClick='" + getEnableEmptyCellClick() + "'" +
            ", enableEmptyCellContextMenu='" + getEnableEmptyCellContextMenu() + "'" +
            ", enableEmptyCellDrop='" + getEnableEmptyCellDrop() + "'" +
            ", enableEmptyCellDrag='" + getEnableEmptyCellDrag() + "'" +
            ", enableOccupiedCellDrop='" + getEnableOccupiedCellDrop() + "'" +
            ", emptyCellDragMaxCols=" + getEmptyCellDragMaxCols() +
            ", emptyCellDragMaxRows=" + getEmptyCellDragMaxRows() +
            ", ignoreMarginInRow='" + getIgnoreMarginInRow() + "'" +
            ", draggable='" + getDraggable() + "'" +
            ", resizable='" + getResizable() + "'" +
            ", swap='" + getSwap() + "'" +
            ", pushItems='" + getPushItems() + "'" +
            ", disablePushOnDrag='" + getDisablePushOnDrag() + "'" +
            ", disablePushOnResize='" + getDisablePushOnResize() + "'" +
            ", pushResizeItems='" + getPushResizeItems() + "'" +
            ", disableWindowResize='" + getDisableWindowResize() + "'" +
            ", disableWarnings='" + getDisableWarnings() + "'" +
            ", scrollToNewItems='" + getScrollToNewItems() + "'" +
            "}";
    }
}
