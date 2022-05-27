import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDashboardConfig, DashboardConfig } from '../dashboard-config.model';
import { DashboardConfigService } from '../service/dashboard-config.service';

@Injectable({ providedIn: 'root' })
export class DashboardConfigRoutingResolveService implements Resolve<IDashboardConfig> {
  constructor(protected service: DashboardConfigService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDashboardConfig> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((dashboardConfig: HttpResponse<DashboardConfig>) => {
          if (dashboardConfig.body) {
            return of(dashboardConfig.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DashboardConfig());
  }
}
