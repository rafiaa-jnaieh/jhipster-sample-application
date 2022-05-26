import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IUserProfile, UserProfile } from '../user-profile.model';
import { UserProfileService } from '../service/user-profile.service';

@Component({
  selector: 'jhi-user-profile-update',
  templateUrl: './user-profile-update.component.html',
})
export class UserProfileUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    priority: [],
  });

  constructor(protected userProfileService: UserProfileService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userProfile }) => {
      this.updateForm(userProfile);
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
    });
  }

  protected createFromForm(): IUserProfile {
    return {
      ...new UserProfile(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      priority: this.editForm.get(['priority'])!.value,
    };
  }
}
