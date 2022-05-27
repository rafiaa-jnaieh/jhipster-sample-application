import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDashboardLayout, DashboardLayout } from '../dashboard-layout.model';
import { DashboardLayoutService } from '../service/dashboard-layout.service';

import { DashboardLayoutRoutingResolveService } from './dashboard-layout-routing-resolve.service';

describe('DashboardLayout routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DashboardLayoutRoutingResolveService;
  let service: DashboardLayoutService;
  let resultDashboardLayout: IDashboardLayout | undefined;

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
    routingResolveService = TestBed.inject(DashboardLayoutRoutingResolveService);
    service = TestBed.inject(DashboardLayoutService);
    resultDashboardLayout = undefined;
  });

  describe('resolve', () => {
    it('should return IDashboardLayout returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDashboardLayout = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultDashboardLayout).toEqual({ id: 'ABC' });
    });

    it('should return new IDashboardLayout if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDashboardLayout = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDashboardLayout).toEqual(new DashboardLayout());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as DashboardLayout })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDashboardLayout = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultDashboardLayout).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
