import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDashboardConfig } from '../dashboard-config.model';
import { DashboardConfigService } from '../service/dashboard-config.service';
import { DashboardConfigDeleteDialogComponent } from '../delete/dashboard-config-delete-dialog.component';

@Component({
  selector: 'jhi-dashboard-config',
  templateUrl: './dashboard-config.component.html',
})
export class DashboardConfigComponent implements OnInit {
  dashboardConfigs?: IDashboardConfig[];
  isLoading = false;

  constructor(protected dashboardConfigService: DashboardConfigService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.dashboardConfigService.query().subscribe({
      next: (res: HttpResponse<IDashboardConfig[]>) => {
        this.isLoading = false;
        this.dashboardConfigs = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IDashboardConfig): string {
    return item.id!;
  }

  delete(dashboardConfig: IDashboardConfig): void {
    const modalRef = this.modalService.open(DashboardConfigDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dashboardConfig = dashboardConfig;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
