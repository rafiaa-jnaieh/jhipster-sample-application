import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDashboardLayout, getDashboardLayoutIdentifier } from '../dashboard-layout.model';

export type EntityResponseType = HttpResponse<IDashboardLayout>;
export type EntityArrayResponseType = HttpResponse<IDashboardLayout[]>;

@Injectable({ providedIn: 'root' })
export class DashboardLayoutService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/dashboard-layouts');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(dashboardLayout: IDashboardLayout): Observable<EntityResponseType> {
    return this.http.post<IDashboardLayout>(this.resourceUrl, dashboardLayout, { observe: 'response' });
  }

  update(dashboardLayout: IDashboardLayout): Observable<EntityResponseType> {
    return this.http.put<IDashboardLayout>(
      `${this.resourceUrl}/${getDashboardLayoutIdentifier(dashboardLayout) as string}`,
      dashboardLayout,
      { observe: 'response' }
    );
  }

  partialUpdate(dashboardLayout: IDashboardLayout): Observable<EntityResponseType> {
    return this.http.patch<IDashboardLayout>(
      `${this.resourceUrl}/${getDashboardLayoutIdentifier(dashboardLayout) as string}`,
      dashboardLayout,
      { observe: 'response' }
    );
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IDashboardLayout>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDashboardLayout[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDashboardLayoutToCollectionIfMissing(
    dashboardLayoutCollection: IDashboardLayout[],
    ...dashboardLayoutsToCheck: (IDashboardLayout | null | undefined)[]
  ): IDashboardLayout[] {
    const dashboardLayouts: IDashboardLayout[] = dashboardLayoutsToCheck.filter(isPresent);
    if (dashboardLayouts.length > 0) {
      const dashboardLayoutCollectionIdentifiers = dashboardLayoutCollection.map(
        dashboardLayoutItem => getDashboardLayoutIdentifier(dashboardLayoutItem)!
      );
      const dashboardLayoutsToAdd = dashboardLayouts.filter(dashboardLayoutItem => {
        const dashboardLayoutIdentifier = getDashboardLayoutIdentifier(dashboardLayoutItem);
        if (dashboardLayoutIdentifier == null || dashboardLayoutCollectionIdentifiers.includes(dashboardLayoutIdentifier)) {
          return false;
        }
        dashboardLayoutCollectionIdentifiers.push(dashboardLayoutIdentifier);
        return true;
      });
      return [...dashboardLayoutsToAdd, ...dashboardLayoutCollection];
    }
    return dashboardLayoutCollection;
  }
}
