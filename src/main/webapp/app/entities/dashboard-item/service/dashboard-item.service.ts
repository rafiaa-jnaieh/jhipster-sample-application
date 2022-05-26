import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDashboardItem, getDashboardItemIdentifier } from '../dashboard-item.model';

export type EntityResponseType = HttpResponse<IDashboardItem>;
export type EntityArrayResponseType = HttpResponse<IDashboardItem[]>;

@Injectable({ providedIn: 'root' })
export class DashboardItemService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/dashboard-items');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(dashboardItem: IDashboardItem): Observable<EntityResponseType> {
    return this.http.post<IDashboardItem>(this.resourceUrl, dashboardItem, { observe: 'response' });
  }

  update(dashboardItem: IDashboardItem): Observable<EntityResponseType> {
    return this.http.put<IDashboardItem>(`${this.resourceUrl}/${getDashboardItemIdentifier(dashboardItem) as string}`, dashboardItem, {
      observe: 'response',
    });
  }

  partialUpdate(dashboardItem: IDashboardItem): Observable<EntityResponseType> {
    return this.http.patch<IDashboardItem>(`${this.resourceUrl}/${getDashboardItemIdentifier(dashboardItem) as string}`, dashboardItem, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IDashboardItem>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDashboardItem[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addDashboardItemToCollectionIfMissing(
    dashboardItemCollection: IDashboardItem[],
    ...dashboardItemsToCheck: (IDashboardItem | null | undefined)[]
  ): IDashboardItem[] {
    const dashboardItems: IDashboardItem[] = dashboardItemsToCheck.filter(isPresent);
    if (dashboardItems.length > 0) {
      const dashboardItemCollectionIdentifiers = dashboardItemCollection.map(
        dashboardItemItem => getDashboardItemIdentifier(dashboardItemItem)!
      );
      const dashboardItemsToAdd = dashboardItems.filter(dashboardItemItem => {
        const dashboardItemIdentifier = getDashboardItemIdentifier(dashboardItemItem);
        if (dashboardItemIdentifier == null || dashboardItemCollectionIdentifiers.includes(dashboardItemIdentifier)) {
          return false;
        }
        dashboardItemCollectionIdentifiers.push(dashboardItemIdentifier);
        return true;
      });
      return [...dashboardItemsToAdd, ...dashboardItemCollection];
    }
    return dashboardItemCollection;
  }
}
