import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { DashboardLayoutComponent } from '../list/dashboard-layout.component';
import { DashboardLayoutDetailComponent } from '../detail/dashboard-layout-detail.component';
import { DashboardLayoutUpdateComponent } from '../update/dashboard-layout-update.component';
import { DashboardLayoutRoutingResolveService } from './dashboard-layout-routing-resolve.service';

const dashboardLayoutRoute: Routes = [
  {
    path: '',
    component: DashboardLayoutComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DashboardLayoutDetailComponent,
    resolve: {
      dashboardLayout: DashboardLayoutRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DashboardLayoutUpdateComponent,
    resolve: {
      dashboardLayout: DashboardLayoutRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DashboardLayoutUpdateComponent,
    resolve: {
      dashboardLayout: DashboardLayoutRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(dashboardLayoutRoute)],
  exports: [RouterModule],
})
export class DashboardLayoutRoutingModule {}
