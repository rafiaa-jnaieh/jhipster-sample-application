import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IDashboardItem, DashboardItem } from '../dashboard-item.model';
import { DashboardItemService } from '../service/dashboard-item.service';
import { IUserProfile } from 'app/entities/user-profile/user-profile.model';
import { UserProfileService } from 'app/entities/user-profile/service/user-profile.service';

@Component({
  selector: 'jhi-dashboard-item-update',
  templateUrl: './dashboard-item-update.component.html',
})
export class DashboardItemUpdateComponent implements OnInit {
  isSaving = false;

  userProfilesSharedCollection: IUserProfile[] = [];

  editForm = this.fb.group({
    id: [],
    cols: [],
    rows: [],
    x: [],
    y: [],
    content: [],
    city: [],
    stateProvince: [],
    userProfile: [],
  });

  constructor(
    protected dashboardItemService: DashboardItemService,
    protected userProfileService: UserProfileService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dashboardItem }) => {
      this.updateForm(dashboardItem);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dashboardItem = this.createFromForm();
    if (dashboardItem.id !== undefined) {
      this.subscribeToSaveResponse(this.dashboardItemService.update(dashboardItem));
    } else {
      this.subscribeToSaveResponse(this.dashboardItemService.create(dashboardItem));
    }
  }

  trackUserProfileById(_index: number, item: IUserProfile): string {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDashboardItem>>): void {
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

  protected updateForm(dashboardItem: IDashboardItem): void {
    this.editForm.patchValue({
      id: dashboardItem.id,
      cols: dashboardItem.cols,
      rows: dashboardItem.rows,
      x: dashboardItem.x,
      y: dashboardItem.y,
      content: dashboardItem.content,
      city: dashboardItem.city,
      stateProvince: dashboardItem.stateProvince,
      userProfile: dashboardItem.userProfile,
    });

    this.userProfilesSharedCollection = this.userProfileService.addUserProfileToCollectionIfMissing(
      this.userProfilesSharedCollection,
      dashboardItem.userProfile
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

  protected createFromForm(): IDashboardItem {
    return {
      ...new DashboardItem(),
      id: this.editForm.get(['id'])!.value,
      cols: this.editForm.get(['cols'])!.value,
      rows: this.editForm.get(['rows'])!.value,
      x: this.editForm.get(['x'])!.value,
      y: this.editForm.get(['y'])!.value,
      content: this.editForm.get(['content'])!.value,
      city: this.editForm.get(['city'])!.value,
      stateProvince: this.editForm.get(['stateProvince'])!.value,
      userProfile: this.editForm.get(['userProfile'])!.value,
    };
  }
}
