import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IDashboardLayout, DashboardLayout } from '../dashboard-layout.model';
import { DashboardLayoutService } from '../service/dashboard-layout.service';
import { IUserProfile } from 'app/entities/user-profile/user-profile.model';
import { UserProfileService } from 'app/entities/user-profile/service/user-profile.service';

@Component({
  selector: 'jhi-dashboard-layout-update',
  templateUrl: './dashboard-layout-update.component.html',
})
export class DashboardLayoutUpdateComponent implements OnInit {
  isSaving = false;

  userProfilesSharedCollection: IUserProfile[] = [];

  editForm = this.fb.group({
    id: [],
    minCols: [],
    minRows: [],
    maxRows: [],
    userProfile: [],
  });

  constructor(
    protected dashboardLayoutService: DashboardLayoutService,
    protected userProfileService: UserProfileService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dashboardLayout }) => {
      this.updateForm(dashboardLayout);

      this.loadRelationshipsOptions();
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

  trackUserProfileById(_index: number, item: IUserProfile): string {
    return item.id!;
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
      userProfile: dashboardLayout.userProfile,
    });

    this.userProfilesSharedCollection = this.userProfileService.addUserProfileToCollectionIfMissing(
      this.userProfilesSharedCollection,
      dashboardLayout.userProfile
    );
  }

  protected loadRelationshipsOptions(): void {
    this.userProfileService
      .query()
      .pipe(map((res: HttpResponse<IUserProfile[]>) => res.body ?? []))
      .pipe(
        map((userProfiles: IUserProfile[]) =>
          this.userProfileService.addUserProfileToCollectionIfMissing(userProfiles, this.editForm.get('userProfile')!.value)
        )
      )
      .subscribe((userProfiles: IUserProfile[]) => (this.userProfilesSharedCollection = userProfiles));
  }

  protected createFromForm(): IDashboardLayout {
    return {
      ...new DashboardLayout(),
      id: this.editForm.get(['id'])!.value,
      minCols: this.editForm.get(['minCols'])!.value,
      minRows: this.editForm.get(['minRows'])!.value,
      maxRows: this.editForm.get(['maxRows'])!.value,
      userProfile: this.editForm.get(['userProfile'])!.value,
    };
  }
}
