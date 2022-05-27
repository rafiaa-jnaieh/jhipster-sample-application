import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IDashboardConfig, DashboardConfig } from '../dashboard-config.model';
import { DashboardConfigService } from '../service/dashboard-config.service';

@Component({
  selector: 'jhi-dashboard-config-update',
  templateUrl: './dashboard-config-update.component.html',
})
export class DashboardConfigUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    margin: [],
    outerMargin: [],
    outerMarginTop: [],
    outerMarginRight: [],
    outerMarginBottom: [],
    outerMarginLeft: [],
    useTransformPositioning: [],
    mobileBreakpoint: [],
    useBodyForBreakpoint: [],
    minCols: [],
    maxCols: [],
    minRows: [],
    maxRows: [],
    maxItemCols: [],
    minItemCols: [],
    maxItemRows: [],
    minItemRows: [],
    maxItemArea: [],
    minItemArea: [],
    defaultItemCols: [],
    defaultItemRows: [],
    fixedColWidth: [],
    fixedRowHeight: [],
    keepFixedHeightInMobile: [],
    keepFixedWidthInMobile: [],
    scrollSensitivity: [],
    scrollSpeed: [],
    enableEmptyCellClick: [],
    enableEmptyCellContextMenu: [],
    enableEmptyCellDrop: [],
    enableEmptyCellDrag: [],
    enableOccupiedCellDrop: [],
    emptyCellDragMaxCols: [],
    emptyCellDragMaxRows: [],
    ignoreMarginInRow: [],
    draggable: [],
    resizable: [],
    swap: [],
    pushItems: [],
    disablePushOnDrag: [],
    disablePushOnResize: [],
    pushResizeItems: [],
    disableWindowResize: [],
    disableWarnings: [],
    scrollToNewItems: [],
  });

  constructor(
    protected dashboardConfigService: DashboardConfigService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dashboardConfig }) => {
      this.updateForm(dashboardConfig);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dashboardConfig = this.createFromForm();
    if (dashboardConfig.id !== undefined) {
      this.subscribeToSaveResponse(this.dashboardConfigService.update(dashboardConfig));
    } else {
      this.subscribeToSaveResponse(this.dashboardConfigService.create(dashboardConfig));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDashboardConfig>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(dashboardConfig: IDashboardConfig): void {
    this.editForm.patchValue({
      id: dashboardConfig.id,
      margin: dashboardConfig.margin,
      outerMargin: dashboardConfig.outerMargin,
      outerMarginTop: dashboardConfig.outerMarginTop,
      outerMarginRight: dashboardConfig.outerMarginRight,
      outerMarginBottom: dashboardConfig.outerMarginBottom,
      outerMarginLeft: dashboardConfig.outerMarginLeft,
      useTransformPositioning: dashboardConfig.useTransformPositioning,
      mobileBreakpoint: dashboardConfig.mobileBreakpoint,
      useBodyForBreakpoint: dashboardConfig.useBodyForBreakpoint,
      minCols: dashboardConfig.minCols,
      maxCols: dashboardConfig.maxCols,
      minRows: dashboardConfig.minRows,
      maxRows: dashboardConfig.maxRows,
      maxItemCols: dashboardConfig.maxItemCols,
      minItemCols: dashboardConfig.minItemCols,
      maxItemRows: dashboardConfig.maxItemRows,
      minItemRows: dashboardConfig.minItemRows,
      maxItemArea: dashboardConfig.maxItemArea,
      minItemArea: dashboardConfig.minItemArea,
      defaultItemCols: dashboardConfig.defaultItemCols,
      defaultItemRows: dashboardConfig.defaultItemRows,
      fixedColWidth: dashboardConfig.fixedColWidth,
      fixedRowHeight: dashboardConfig.fixedRowHeight,
      keepFixedHeightInMobile: dashboardConfig.keepFixedHeightInMobile,
      keepFixedWidthInMobile: dashboardConfig.keepFixedWidthInMobile,
      scrollSensitivity: dashboardConfig.scrollSensitivity,
      scrollSpeed: dashboardConfig.scrollSpeed,
      enableEmptyCellClick: dashboardConfig.enableEmptyCellClick,
      enableEmptyCellContextMenu: dashboardConfig.enableEmptyCellContextMenu,
      enableEmptyCellDrop: dashboardConfig.enableEmptyCellDrop,
      enableEmptyCellDrag: dashboardConfig.enableEmptyCellDrag,
      enableOccupiedCellDrop: dashboardConfig.enableOccupiedCellDrop,
      emptyCellDragMaxCols: dashboardConfig.emptyCellDragMaxCols,
      emptyCellDragMaxRows: dashboardConfig.emptyCellDragMaxRows,
      ignoreMarginInRow: dashboardConfig.ignoreMarginInRow,
      draggable: dashboardConfig.draggable,
      resizable: dashboardConfig.resizable,
      swap: dashboardConfig.swap,
      pushItems: dashboardConfig.pushItems,
      disablePushOnDrag: dashboardConfig.disablePushOnDrag,
      disablePushOnResize: dashboardConfig.disablePushOnResize,
      pushResizeItems: dashboardConfig.pushResizeItems,
      disableWindowResize: dashboardConfig.disableWindowResize,
      disableWarnings: dashboardConfig.disableWarnings,
      scrollToNewItems: dashboardConfig.scrollToNewItems,
    });
  }

  protected createFromForm(): IDashboardConfig {
    return {
      ...new DashboardConfig(),
      id: this.editForm.get(['id'])!.value,
      margin: this.editForm.get(['margin'])!.value,
      outerMargin: this.editForm.get(['outerMargin'])!.value,
      outerMarginTop: this.editForm.get(['outerMarginTop'])!.value,
      outerMarginRight: this.editForm.get(['outerMarginRight'])!.value,
      outerMarginBottom: this.editForm.get(['outerMarginBottom'])!.value,
      outerMarginLeft: this.editForm.get(['outerMarginLeft'])!.value,
      useTransformPositioning: this.editForm.get(['useTransformPositioning'])!.value,
      mobileBreakpoint: this.editForm.get(['mobileBreakpoint'])!.value,
      useBodyForBreakpoint: this.editForm.get(['useBodyForBreakpoint'])!.value,
      minCols: this.editForm.get(['minCols'])!.value,
      maxCols: this.editForm.get(['maxCols'])!.value,
      minRows: this.editForm.get(['minRows'])!.value,
      maxRows: this.editForm.get(['maxRows'])!.value,
      maxItemCols: this.editForm.get(['maxItemCols'])!.value,
      minItemCols: this.editForm.get(['minItemCols'])!.value,
      maxItemRows: this.editForm.get(['maxItemRows'])!.value,
      minItemRows: this.editForm.get(['minItemRows'])!.value,
      maxItemArea: this.editForm.get(['maxItemArea'])!.value,
      minItemArea: this.editForm.get(['minItemArea'])!.value,
      defaultItemCols: this.editForm.get(['defaultItemCols'])!.value,
      defaultItemRows: this.editForm.get(['defaultItemRows'])!.value,
      fixedColWidth: this.editForm.get(['fixedColWidth'])!.value,
      fixedRowHeight: this.editForm.get(['fixedRowHeight'])!.value,
      keepFixedHeightInMobile: this.editForm.get(['keepFixedHeightInMobile'])!.value,
      keepFixedWidthInMobile: this.editForm.get(['keepFixedWidthInMobile'])!.value,
      scrollSensitivity: this.editForm.get(['scrollSensitivity'])!.value,
      scrollSpeed: this.editForm.get(['scrollSpeed'])!.value,
      enableEmptyCellClick: this.editForm.get(['enableEmptyCellClick'])!.value,
      enableEmptyCellContextMenu: this.editForm.get(['enableEmptyCellContextMenu'])!.value,
      enableEmptyCellDrop: this.editForm.get(['enableEmptyCellDrop'])!.value,
      enableEmptyCellDrag: this.editForm.get(['enableEmptyCellDrag'])!.value,
      enableOccupiedCellDrop: this.editForm.get(['enableOccupiedCellDrop'])!.value,
      emptyCellDragMaxCols: this.editForm.get(['emptyCellDragMaxCols'])!.value,
      emptyCellDragMaxRows: this.editForm.get(['emptyCellDragMaxRows'])!.value,
      ignoreMarginInRow: this.editForm.get(['ignoreMarginInRow'])!.value,
      draggable: this.editForm.get(['draggable'])!.value,
      resizable: this.editForm.get(['resizable'])!.value,
      swap: this.editForm.get(['swap'])!.value,
      pushItems: this.editForm.get(['pushItems'])!.value,
      disablePushOnDrag: this.editForm.get(['disablePushOnDrag'])!.value,
      disablePushOnResize: this.editForm.get(['disablePushOnResize'])!.value,
      pushResizeItems: this.editForm.get(['pushResizeItems'])!.value,
      disableWindowResize: this.editForm.get(['disableWindowResize'])!.value,
      disableWarnings: this.editForm.get(['disableWarnings'])!.value,
      scrollToNewItems: this.editForm.get(['scrollToNewItems'])!.value,
    };
  }
}
