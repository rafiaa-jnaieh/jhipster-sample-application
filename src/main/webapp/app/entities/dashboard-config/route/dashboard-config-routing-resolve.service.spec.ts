import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDashboardConfig, DashboardConfig } from '../dashboard-config.model';
import { DashboardConfigService } from '../service/dashboard-config.service';

import { DashboardConfigRoutingResolveService } from './dashboard-config-routing-resolve.service';

describe('DashboardConfig routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DashboardConfigRoutingResolveService;
  let service: DashboardConfigService;
  let resultDashboardConfig: IDashboardConfig | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(DashboardConfigRoutingResolveService);
    service = TestBed.inject(DashboardConfigService);
    resultDashboardConfig = undefined;
  });

  describe('resolve', () => {
    it('should return IDashboardConfig returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDashboardConfig = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultDashboardConfig).toEqual({ id: 'ABC' });
    });

    it('should return new IDashboardConfig if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDashboardConfig = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDashboardConfig).toEqual(new DashboardConfig());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as DashboardConfig })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDashboardConfig = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultDashboardConfig).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
