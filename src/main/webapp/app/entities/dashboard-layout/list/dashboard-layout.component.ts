import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDashboardLayout } from '../dashboard-layout.model';
import { DashboardLayoutService } from '../service/dashboard-layout.service';
import { DashboardLayoutDeleteDialogComponent } from '../delete/dashboard-layout-delete-dialog.component';

@Component({
  selector: 'jhi-dashboard-layout',
  templateUrl: './dashboard-layout.component.html',
})
export class DashboardLayoutComponent implements OnInit {
  dashboardLayouts?: IDashboardLayout[];
  isLoading = false;

  constructor(protected dashboardLayoutService: DashboardLayoutService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.dashboardLayoutService.query().subscribe({
      next: (res: HttpResponse<IDashboardLayout[]>) => {
        this.isLoading = false;
        this.dashboardLayouts = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IDashboardLayout): string {
    return item.id!;
  }

  delete(dashboardLayout: IDashboardLayout): void {
    const modalRef = this.modalService.open(DashboardLayoutDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dashboardLayout = dashboardLayout;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
