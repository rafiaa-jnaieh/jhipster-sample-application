import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DashboardLayoutComponent } from './list/dashboard-layout.component';
import { DashboardLayoutDetailComponent } from './detail/dashboard-layout-detail.component';
import { DashboardLayoutUpdateComponent } from './update/dashboard-layout-update.component';
import { DashboardLayoutDeleteDialogComponent } from './delete/dashboard-layout-delete-dialog.component';
import { DashboardLayoutRoutingModule } from './route/dashboard-layout-routing.module';

@NgModule({
  imports: [SharedModule, DashboardLayoutRoutingModule],
  declarations: [
    DashboardLayoutComponent,
    DashboardLayoutDetailComponent,
    DashboardLayoutUpdateComponent,
    DashboardLayoutDeleteDialogComponent,
  ],
  entryComponents: [DashboardLayoutDeleteDialogComponent],
})
export class DashboardLayoutModule {}
