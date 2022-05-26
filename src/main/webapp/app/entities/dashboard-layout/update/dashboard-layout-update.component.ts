import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IDashboardLayout, DashboardLayout } from '../dashboard-layout.model';
import { DashboardLayoutService } from '../service/dashboard-layout.service';

@Component({
  selector: 'jhi-dashboard-layout-update',
  templateUrl: './dashboard-layout-update.component.html',
})
export class DashboardLayoutUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    minCols: [],
    minRows: [],
    maxRows: [],
  });

  constructor(
    protected dashboardLayoutService: DashboardLayoutService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dashboardLayout }) => {
      this.updateForm(dashboardLayout);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dashboardLayout = this.createFromForm();
    if (dashboardLayout.id !== undefined) {
      this.subscribeToSaveResponse(this.dashboardLayoutService.update(dashboardLayout));
    } else {
      this.subscribeToSaveResponse(this.dashboardLayoutService.create(dashboardLayout));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDashboardLayout>>): void {
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

  protected updateForm(dashboardLayout: IDashboardLayout): void {
    this.editForm.patchValue({
      id: dashboardLayout.id,
      minCols: dashboardLayout.minCols,
      minRows: dashboardLayout.minRows,
      maxRows: dashboardLayout.maxRows,
    });
  }

  protected createFromForm(): IDashboardLayout {
    return {
      ...new DashboardLayout(),
      id: this.editForm.get(['id'])!.value,
      minCols: this.editForm.get(['minCols'])!.value,
      minRows: this.editForm.get(['minRows'])!.value,
      maxRows: this.editForm.get(['maxRows'])!.value,
    };
  }
}
