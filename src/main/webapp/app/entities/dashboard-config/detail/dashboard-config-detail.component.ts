import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDashboardConfig } from '../dashboard-config.model';

@Component({
  selector: 'jhi-dashboard-config-detail',
  templateUrl: './dashboard-config-detail.component.html',
})
export class DashboardConfigDetailComponent implements OnInit {
  dashboardConfig: IDashboardConfig | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dashboardConfig }) => {
      this.dashboardConfig = dashboardConfig;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
