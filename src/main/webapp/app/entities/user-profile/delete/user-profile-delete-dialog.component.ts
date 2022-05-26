import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserProfile } from '../user-profile.model';
import { UserProfileService } from '../service/user-profile.service';

@Component({
  templateUrl: './user-profile-delete-dialog.component.html',
})
export class UserProfileDeleteDialogComponent {
  userProfile?: IUserProfile;

  constructor(protected userProfileService: UserProfileService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.userProfileService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
