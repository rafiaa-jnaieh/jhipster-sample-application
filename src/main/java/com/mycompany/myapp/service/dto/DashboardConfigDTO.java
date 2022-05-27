package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.DashboardConfig} entity.
 */
public class DashboardConfigDTO implements Serializable {

    private String id;

    private Integer margin;

    private Boolean outerMargin;

    private Integer outerMarginTop;

    private Integer outerMarginRight;

    private Integer outerMarginBottom;

    private Integer outerMarginLeft;

    private Boolean useTransformPositioning;

    private Integer mobileBreakpoint;

    private Boolean useBodyForBreakpoint;

    private Integer minCols;

    private Integer maxCols;

    private Integer minRows;

    private Integer maxRows;

    private Integer maxItemCols;

    private Integer minItemCols;

    private Integer maxItemRows;

    private Integer minItemRows;

    private Integer maxItemArea;

    private Integer minItemArea;

    private Integer defaultItemCols;

    private Integer defaultItemRows;

    private Integer fixedColWidth;

    private Integer fixedRowHeight;

    private Boolean keepFixedHeightInMobile;

    private Boolean keepFixedWidthInMobile;

    private Integer scrollSensitivity;

    private Integer scrollSpeed;

    private Boolean enableEmptyCellClick;

    private Boolean enableEmptyCellContextMenu;

    private Boolean enableEmptyCellDrop;

    private Boolean enableEmptyCellDrag;

    private Boolean enableOccupiedCellDrop;

    private Integer emptyCellDragMaxCols;

    private Integer emptyCellDragMaxRows;

    private Boolean ignoreMarginInRow;

    private Boolean draggable;

    private Boolean resizable;

    private Boolean swap;

    private Boolean pushItems;

    private Boolean disablePushOnDrag;

    private Boolean disablePushOnResize;

    private Boolean pushResizeItems;

    private Boolean disableWindowResize;

    private Boolean disableWarnings;

    private Boolean scrollToNewItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMargin() {
        return margin;
    }

    public void setMargin(Integer margin) {
        this.margin = margin;
    }

    public Boolean getOuterMargin() {
        return outerMargin;
    }

    public void setOuterMargin(Boolean outerMargin) {
        this.outerMargin = outerMargin;
    }

    public Integer getOuterMarginTop() {
        return outerMarginTop;
    }

    public void setOuterMarginTop(Integer outerMarginTop) {
        this.outerMarginTop = outerMarginTop;
    }

    public Integer getOuterMarginRight() {
        return outerMarginRight;
    }

    public void setOuterMarginRight(Integer outerMarginRight) {
        this.outerMarginRight = outerMarginRight;
    }

    public Integer getOuterMarginBottom() {
        return outerMarginBottom;
    }

    public void setOuterMarginBottom(Integer outerMarginBottom) {
        this.outerMarginBottom = outerMarginBottom;
    }

    public Integer getOuterMarginLeft() {
        return outerMarginLeft;
    }

    public void setOuterMarginLeft(Integer outerMarginLeft) {
        this.outerMarginLeft = outerMarginLeft;
    }

    public Boolean getUseTransformPositioning() {
        return useTransformPositioning;
    }

    public void setUseTransformPositioning(Boolean useTransformPositioning) {
        this.useTransformPositioning = useTransformPositioning;
    }

    public Integer getMobileBreakpoint() {
        return mobileBreakpoint;
    }

    public void setMobileBreakpoint(Integer mobileBreakpoint) {
        this.mobileBreakpoint = mobileBreakpoint;
    }

    public Boolean getUseBodyForBreakpoint() {
        return useBodyForBreakpoint;
    }

    public void setUseBodyForBreakpoint(Boolean useBodyForBreakpoint) {
        this.useBodyForBreakpoint = useBodyForBreakpoint;
    }

    public Integer getMinCols() {
        return minCols;
    }

    public void setMinCols(Integer minCols) {
        this.minCols = minCols;
    }

    public Integer getMaxCols() {
        return maxCols;
    }

    public void setMaxCols(Integer maxCols) {
        this.maxCols = maxCols;
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

    public Integer getMaxItemCols() {
        return maxItemCols;
    }

    public void setMaxItemCols(Integer maxItemCols) {
        this.maxItemCols = maxItemCols;
    }

    public Integer getMinItemCols() {
        return minItemCols;
    }

    public void setMinItemCols(Integer minItemCols) {
        this.minItemCols = minItemCols;
    }

    public Integer getMaxItemRows() {
        return maxItemRows;
    }

    public void setMaxItemRows(Integer maxItemRows) {
        this.maxItemRows = maxItemRows;
    }

    public Integer getMinItemRows() {
        return minItemRows;
    }

    public void setMinItemRows(Integer minItemRows) {
        this.minItemRows = minItemRows;
    }

    public Integer getMaxItemArea() {
        return maxItemArea;
    }

    public void setMaxItemArea(Integer maxItemArea) {
        this.maxItemArea = maxItemArea;
    }

    public Integer getMinItemArea() {
        return minItemArea;
    }

    public void setMinItemArea(Integer minItemArea) {
        this.minItemArea = minItemArea;
    }

    public Integer getDefaultItemCols() {
        return defaultItemCols;
    }

    public void setDefaultItemCols(Integer defaultItemCols) {
        this.defaultItemCols = defaultItemCols;
    }

    public Integer getDefaultItemRows() {
        return defaultItemRows;
    }

    public void setDefaultItemRows(Integer defaultItemRows) {
        this.defaultItemRows = defaultItemRows;
    }

    public Integer getFixedColWidth() {
        return fixedColWidth;
    }

    public void setFixedColWidth(Integer fixedColWidth) {
        this.fixedColWidth = fixedColWidth;
    }

    public Integer getFixedRowHeight() {
        return fixedRowHeight;
    }

    public void setFixedRowHeight(Integer fixedRowHeight) {
        this.fixedRowHeight = fixedRowHeight;
    }

    public Boolean getKeepFixedHeightInMobile() {
        return keepFixedHeightInMobile;
    }

    public void setKeepFixedHeightInMobile(Boolean keepFixedHeightInMobile) {
        this.keepFixedHeightInMobile = keepFixedHeightInMobile;
    }

    public Boolean getKeepFixedWidthInMobile() {
        return keepFixedWidthInMobile;
    }

    public void setKeepFixedWidthInMobile(Boolean keepFixedWidthInMobile) {
        this.keepFixedWidthInMobile = keepFixedWidthInMobile;
    }

    public Integer getScrollSensitivity() {
        return scrollSensitivity;
    }

    public void setScrollSensitivity(Integer scrollSensitivity) {
        this.scrollSensitivity = scrollSensitivity;
    }

    public Integer getScrollSpeed() {
        return scrollSpeed;
    }

    public void setScrollSpeed(Integer scrollSpeed) {
        this.scrollSpeed = scrollSpeed;
    }

    public Boolean getEnableEmptyCellClick() {
        return enableEmptyCellClick;
    }

    public void setEnableEmptyCellClick(Boolean enableEmptyCellClick) {
        this.enableEmptyCellClick = enableEmptyCellClick;
    }

    public Boolean getEnableEmptyCellContextMenu() {
        return enableEmptyCellContextMenu;
    }

    public void setEnableEmptyCellContextMenu(Boolean enableEmptyCellContextMenu) {
        this.enableEmptyCellContextMenu = enableEmptyCellContextMenu;
    }

    public Boolean getEnableEmptyCellDrop() {
        return enableEmptyCellDrop;
    }

    public void setEnableEmptyCellDrop(Boolean enableEmptyCellDrop) {
        this.enableEmptyCellDrop = enableEmptyCellDrop;
    }

    public Boolean getEnableEmptyCellDrag() {
        return enableEmptyCellDrag;
    }

    public void setEnableEmptyCellDrag(Boolean enableEmptyCellDrag) {
        this.enableEmptyCellDrag = enableEmptyCellDrag;
    }

    public Boolean getEnableOccupiedCellDrop() {
        return enableOccupiedCellDrop;
    }

    public void setEnableOccupiedCellDrop(Boolean enableOccupiedCellDrop) {
        this.enableOccupiedCellDrop = enableOccupiedCellDrop;
    }

    public Integer getEmptyCellDragMaxCols() {
        return emptyCellDragMaxCols;
    }

    public void setEmptyCellDragMaxCols(Integer emptyCellDragMaxCols) {
        this.emptyCellDragMaxCols = emptyCellDragMaxCols;
    }

    public Integer getEmptyCellDragMaxRows() {
        return emptyCellDragMaxRows;
    }

    public void setEmptyCellDragMaxRows(Integer emptyCellDragMaxRows) {
        this.emptyCellDragMaxRows = emptyCellDragMaxRows;
    }

    public Boolean getIgnoreMarginInRow() {
        return ignoreMarginInRow;
    }

    public void setIgnoreMarginInRow(Boolean ignoreMarginInRow) {
        this.ignoreMarginInRow = ignoreMarginInRow;
    }

    public Boolean getDraggable() {
        return draggable;
    }

    public void setDraggable(Boolean draggable) {
        this.draggable = draggable;
    }

    public Boolean getResizable() {
        return resizable;
    }

    public void setResizable(Boolean resizable) {
        this.resizable = resizable;
    }

    public Boolean getSwap() {
        return swap;
    }

    public void setSwap(Boolean swap) {
        this.swap = swap;
    }

    public Boolean getPushItems() {
        return pushItems;
    }

    public void setPushItems(Boolean pushItems) {
        this.pushItems = pushItems;
    }

    public Boolean getDisablePushOnDrag() {
        return disablePushOnDrag;
    }

    public void setDisablePushOnDrag(Boolean disablePushOnDrag) {
        this.disablePushOnDrag = disablePushOnDrag;
    }

    public Boolean getDisablePushOnResize() {
        return disablePushOnResize;
    }

    public void setDisablePushOnResize(Boolean disablePushOnResize) {
        this.disablePushOnResize = disablePushOnResize;
    }

    public Boolean getPushResizeItems() {
        return pushResizeItems;
    }

    public void setPushResizeItems(Boolean pushResizeItems) {
        this.pushResizeItems = pushResizeItems;
    }

    public Boolean getDisableWindowResize() {
        return disableWindowResize;
    }

    public void setDisableWindowResize(Boolean disableWindowResize) {
        this.disableWindowResize = disableWindowResize;
    }

    public Boolean getDisableWarnings() {
        return disableWarnings;
    }

    public void setDisableWarnings(Boolean disableWarnings) {
        this.disableWarnings = disableWarnings;
    }

    public Boolean getScrollToNewItems() {
        return scrollToNewItems;
    }

    public void setScrollToNewItems(Boolean scrollToNewItems) {
        this.scrollToNewItems = scrollToNewItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DashboardConfigDTO)) {
            return false;
        }

        DashboardConfigDTO dashboardConfigDTO = (DashboardConfigDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dashboardConfigDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DashboardConfigDTO{" +
            "id='" + getId() + "'" +
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
