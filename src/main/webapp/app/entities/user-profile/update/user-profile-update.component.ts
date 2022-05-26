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
import { IDashboardItem } from 'app/entities/dashboard-item/dashboard-item.model';
import { DashboardItemService } from 'app/entities/dashboard-item/service/dashboard-item.service';

@Component({
  selector: 'jhi-user-profile-update',
  templateUrl: './user-profile-update.component.html',
})
export class UserProfileUpdateComponent implements OnInit {
  isSaving = false;

  dashboardLayoutsSharedCollection: IDashboardLayout[] = [];
  dashboardItemsSharedCollection: IDashboardItem[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    priority: [],
    dashboardLayout: [],
    dashboardItem: [],
  });

  constructor(
    protected userProfileService: UserProfileService,
    protected dashboardLayoutService: DashboardLayoutService,
    protected dashboardItemService: DashboardItemService,
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

  trackDashboardItemById(_index: number, item: IDashboardItem): string {
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
      dashboardItem: userProfile.dashboardItem,
    });

    this.dashboardLayoutsSharedCollection = this.dashboardLayoutService.addDashboardLayoutToCollectionIfMissing(
      this.dashboardLayoutsSharedCollection,
      userProfile.dashboardLayout
    );
    this.dashboardItemsSharedCollection = this.dashboardItemService.addDashboardItemToCollectionIfMissing(
      this.dashboardItemsSharedCollection,
      userProfile.dashboardItem
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

    this.dashboardItemService
      .query()
      .pipe(map((res: HttpResponse<IDashboardItem[]>) => res.body ?? []))
      .pipe(
        map((dashboardItems: IDashboardItem[]) =>
          this.dashboardItemService.addDashboardItemToCollectionIfMissing(dashboardItems, this.editForm.get('dashboardItem')!.value)
        )
      )
      .subscribe((dashboardItems: IDashboardItem[]) => (this.dashboardItemsSharedCollection = dashboardItems));
  }

  protected createFromForm(): IUserProfile {
    return {
      ...new UserProfile(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      priority: this.editForm.get(['priority'])!.value,
      dashboardLayout: this.editForm.get(['dashboardLayout'])!.value,
      dashboardItem: this.editForm.get(['dashboardItem'])!.value,
    };
  }
}
