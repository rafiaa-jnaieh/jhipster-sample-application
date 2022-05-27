import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDashboardItem } from '../dashboard-item.model';
import { DashboardItemService } from '../service/dashboard-item.service';
import { DashboardItemDeleteDialogComponent } from '../delete/dashboard-item-delete-dialog.component';

@Component({
  selector: 'jhi-dashboard-item',
  templateUrl: './dashboard-item.component.html',
})
export class DashboardItemComponent implements OnInit {
  dashboardItems?: IDashboardItem[];
  isLoading = false;

  constructor(protected dashboardItemService: DashboardItemService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.dashboardItemService.query().subscribe({
      next: (res: HttpResponse<IDashboardItem[]>) => {
        this.isLoading = false;
        this.dashboardItems = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IDashboardItem): string {
    return item.id!;
  }

  delete(dashboardItem: IDashboardItem): void {
    const modalRef = this.modalService.open(DashboardItemDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dashboardItem = dashboardItem;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
