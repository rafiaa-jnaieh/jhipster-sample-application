import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDashboardLayout } from '../dashboard-layout.model';
import { DashboardLayoutService } from '../service/dashboard-layout.service';

@Component({
  templateUrl: './dashboard-layout-delete-dialog.component.html',
})
export class DashboardLayoutDeleteDialogComponent {
  dashboardLayout?: IDashboardLayout;

  constructor(protected dashboardLayoutService: DashboardLayoutService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.dashboardLayoutService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
