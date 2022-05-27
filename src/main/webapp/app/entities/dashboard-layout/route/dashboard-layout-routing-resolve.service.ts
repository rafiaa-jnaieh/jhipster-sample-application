import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDashboardLayout, DashboardLayout } from '../dashboard-layout.model';
import { DashboardLayoutService } from '../service/dashboard-layout.service';

@Injectable({ providedIn: 'root' })
export class DashboardLayoutRoutingResolveService implements Resolve<IDashboardLayout> {
  constructor(protected service: DashboardLayoutService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDashboardLayout> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((dashboardLayout: HttpResponse<DashboardLayout>) => {
          if (dashboardLayout.body) {
            return of(dashboardLayout.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DashboardLayout());
  }
}
