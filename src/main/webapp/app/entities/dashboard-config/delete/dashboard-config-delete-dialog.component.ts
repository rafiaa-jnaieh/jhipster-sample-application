import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDashboardConfig } from '../dashboard-config.model';
import { DashboardConfigService } from '../service/dashboard-config.service';

@Component({
  templateUrl: './dashboard-config-delete-dialog.component.html',
})
export class DashboardConfigDeleteDialogComponent {
  dashboardConfig?: IDashboardConfig;

  constructor(protected dashboardConfigService: DashboardConfigService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.dashboardConfigService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
