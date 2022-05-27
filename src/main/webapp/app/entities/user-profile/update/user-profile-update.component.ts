import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IUserProfile, UserProfile } from '../user-profile.model';
import { UserProfileService } from '../service/user-profile.service';
import { IDashboardLayout } from 'app/entities/dashboard-layout/dashboard-layout.model';
import { DashboardLayoutService } from 'app/entities/dashboard-layout/service/dashboard-layout.service';
import { IDashboardConfig } from 'app/entities/dashboard-config/dashboard-config.model';
import { DashboardConfigService } from 'app/entities/dashboard-config/service/dashboard-config.service';

@Component({
  selector: 'jhi-user-profile-update',
  templateUrl: './user-profile-update.component.html',
})
export class UserProfileUpdateComponent implements OnInit {
  isSaving = false;

  dashboardLayoutsSharedCollection: IDashboardLayout[] = [];
  dashboardConfigsSharedCollection: IDashboardConfig[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    priority: [],
    dashboardLayout: [],
    dashboardConfig: [],
  });

  constructor(
    protected userProfileService: UserProfileService,
    protected dashboardLayoutService: DashboardLayoutService,
    protected dashboardConfigService: DashboardConfigService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userProfile }) => {
      this.updateForm(userProfile);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userProfile = this.createFromForm();
    if (userProfile.id !== undefined) {
      this.subscribeToSaveResponse(this.userProfileService.update(userProfile));
    } else {
      this.subscribeToSaveResponse(this.userProfileService.create(userProfile));
    }
  }

  trackDashboardLayoutById(_index: number, item: IDashboardLayout): string {
    return item.id!;
  }

  trackDashboardConfigById(_index: number, item: IDashboardConfig): string {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserProfile>>): void {
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

  protected updateForm(userProfile: IUserProfile): void {
    this.editForm.patchValue({
      id: userProfile.id,
      name: userProfile.name,
      priority: userProfile.priority,
      dashboardLayout: userProfile.dashboardLayout,
      dashboardConfig: userProfile.dashboardConfig,
    });

    this.dashboardLayoutsSharedCollection = this.dashboardLayoutService.addDashboardLayoutToCollectionIfMissing(
      this.dashboardLayoutsSharedCollection,
      userProfile.dashboardLayout
    );
    this.dashboardConfigsSharedCollection = this.dashboardConfigService.addDashboardConfigToCollectionIfMissing(
      this.dashboardConfigsSharedCollection,
      userProfile.dashboardConfig
    );
  }

  protected loadRelationshipsOptions(): void {
    this.dashboardLayoutService
      .query()
      .pipe(map((res: HttpResponse<IDashboardLayout[]>) => res.body ?? []))
      .pipe(
        map((dashboardLayouts: IDashboardLayout[]) =>
          this.dashboardLayoutService.addDashboardLayoutToCollectionIfMissing(dashboardLayouts, this.editForm.get('dashboardLayout')!.value)
        )
      )
      .subscribe((dashboardLayouts: IDashboardLayout[]) => (this.dashboardLayoutsSharedCollection = dashboardLayouts));

    this.dashboardConfigService
      .query()
      .pipe(map((res: HttpResponse<IDashboardConfig[]>) => res.body ?? []))
      .pipe(
        map((dashboardConfigs: IDashboardConfig[]) =>
          this.dashboardConfigService.addDashboardConfigToCollectionIfMissing(dashboardConfigs, this.editForm.get('dashboardConfig')!.value)
        )
      )
      .subscribe((dashboardConfigs: IDashboardConfig[]) => (this.dashboardConfigsSharedCollection = dashboardConfigs));
  }

  protected createFromForm(): IUserProfile {
    return {
      ...new UserProfile(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      priority: this.editForm.get(['priority'])!.value,
      dashboardLayout: this.editForm.get(['dashboardLayout'])!.value,
      dashboardConfig: this.editForm.get(['dashboardConfig'])!.value,
    };
  }
}
