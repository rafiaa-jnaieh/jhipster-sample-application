import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DashboardConfigComponent } from '../list/dashboard-config.component';
import { DashboardConfigDetailComponent } from '../detail/dashboard-config-detail.component';
import { DashboardConfigUpdateComponent } from '../update/dashboard-config-update.component';
import { DashboardConfigRoutingResolveService } from './dashboard-config-routing-resolve.service';

const dashboardConfigRoute: Routes = [
  {
    path: '',
    component: DashboardConfigComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DashboardConfigDetailComponent,
    resolve: {
      dashboardConfig: DashboardConfigRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DashboardConfigUpdateComponent,
    resolve: {
      dashboardConfig: DashboardConfigRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DashboardConfigUpdateComponent,
    resolve: {
      dashboardConfig: DashboardConfigRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(dashboardConfigRoute)],
  exports: [RouterModule],
})
export class DashboardConfigRoutingModule {}
