import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDashboardLayout } from '../dashboard-layout.model';

@Component({
  selector: 'jhi-dashboard-layout-detail',
  templateUrl: './dashboard-layout-detail.component.html',
})
export class DashboardLayoutDetailComponent implements OnInit {
  dashboardLayout: IDashboardLayout | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dashboardLayout }) => {
      this.dashboardLayout = dashboardLayout;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
