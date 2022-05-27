import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DashboardConfigComponent } from './list/dashboard-config.component';
import { DashboardConfigDetailComponent } from './detail/dashboard-config-detail.component';
import { DashboardConfigUpdateComponent } from './update/dashboard-config-update.component';
import { DashboardConfigDeleteDialogComponent } from './delete/dashboard-config-delete-dialog.component';
import { DashboardConfigRoutingModule } from './route/dashboard-config-routing.module';

@NgModule({
  imports: [SharedModule, DashboardConfigRoutingModule],
  declarations: [
    DashboardConfigComponent,
    DashboardConfigDetailComponent,
    DashboardConfigUpdateComponent,
    DashboardConfigDeleteDialogComponent,
  ],
  entryComponents: [DashboardConfigDeleteDialogComponent],
})
export class DashboardConfigModule {}
