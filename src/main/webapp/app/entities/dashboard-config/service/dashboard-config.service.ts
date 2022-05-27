import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDashboardConfig, getDashboardConfigIdentifier } from '../dashboard-config.model';

export type EntityResponseType = HttpResponse<IDashboardConfig>;
export type EntityArrayResponseType = HttpResponse<IDashboardConfig[]>;

@Injectable({ providedIn: 'root' })
export class DashboardConfigService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/dashboard-configs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(dashboardConfig: IDashboardConfig): Observable<EntityResponseType> {
    return this.http.post<IDashboardConfig>(this.resourceUrl, dashboardConfig, { observe: 'response' });
  }

  update(dashboardConfig: IDashboardConfig): Observable<EntityResponseType> {
    return this.http.put<IDashboardConfig>(
      `${this.resourceUrl}/${getDashboardConfigIdentifier(dashboardConfig) as string}`,
      dashboardConfig,
      { observe: 'response' }
    );
  }

  partialUpdate(dashboardConfig: IDashboardConfig): Observable<EntityResponseType> {
    return this.http.patch<IDashboardConfig>(
      `${this.resourceUrl}/${getDashboardConfigIdentifier(dashboardConfig) as string}`,
      dashboardConfig,
      { observe: 'response' }
    );
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IDashboardConfig>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDashboardConfig[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDashboardConfigToCollectionIfMissing(
    dashboardConfigCollection: IDashboardConfig[],
    ...dashboardConfigsToCheck: (IDashboardConfig | null | undefined)[]
  ): IDashboardConfig[] {
    const dashboardConfigs: IDashboardConfig[] = dashboardConfigsToCheck.filter(isPresent);
    if (dashboardConfigs.length > 0) {
      const dashboardConfigCollectionIdentifiers = dashboardConfigCollection.map(
        dashboardConfigItem => getDashboardConfigIdentifier(dashboardConfigItem)!
      );
      const dashboardConfigsToAdd = dashboardConfigs.filter(dashboardConfigItem => {
        const dashboardConfigIdentifier = getDashboardConfigIdentifier(dashboardConfigItem);
        if (dashboardConfigIdentifier == null || dashboardConfigCollectionIdentifiers.includes(dashboardConfigIdentifier)) {
          return false;
        }
        dashboardConfigCollectionIdentifiers.push(dashboardConfigIdentifier);
        return true;
      });
      return [...dashboardConfigsToAdd, ...dashboardConfigCollection];
    }
    return dashboardConfigCollection;
  }
}
