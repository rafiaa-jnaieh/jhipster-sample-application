import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDashboardLayout, DashboardLayout } from '../dashboard-layout.model';

import { DashboardLayoutService } from './dashboard-layout.service';

describe('DashboardLayout Service', () => {
  let service: DashboardLayoutService;
  let httpMock: HttpTestingController;
  let elemDefault: IDashboardLayout;
  let expectedResult: IDashboardLayout | IDashboardLayout[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DashboardLayoutService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 'AAAAAAA',
      minCols: 0,
      minRows: 0,
      maxRows: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a DashboardLayout', () => {
      const returnedFromService = Object.assign(
        {
          id: 'ID',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new DashboardLayout()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DashboardLayout', () => {
      const returnedFromService = Object.assign(
        {
          id: 'BBBBBB',
          minCols: 1,
          minRows: 1,
          maxRows: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DashboardLayout', () => {
      const patchObject = Object.assign(
        {
          minCols: 1,
          maxRows: 1,
        },
        new DashboardLayout()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DashboardLayout', () => {
      const returnedFromService = Object.assign(
        {
          id: 'BBBBBB',
          minCols: 1,
          minRows: 1,
          maxRows: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a DashboardLayout', () => {
      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDashboardLayoutToCollectionIfMissing', () => {
      it('should add a DashboardLayout to an empty array', () => {
        const dashboardLayout: IDashboardLayout = { id: 'ABC' };
        expectedResult = service.addDashboardLayoutToCollectionIfMissing([], dashboardLayout);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(dashboardLayout);
      });

      it('should not add a DashboardLayout to an array that contains it', () => {
        const dashboardLayout: IDashboardLayout = { id: 'ABC' };
        const dashboardLayoutCollection: IDashboardLayout[] = [
          {
            ...dashboardLayout,
          },
          { id: 'CBA' },
        ];
        expectedResult = service.addDashboardLayoutToCollectionIfMissing(dashboardLayoutCollection, dashboardLayout);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DashboardLayout to an array that doesn't contain it", () => {
        const dashboardLayout: IDashboardLayout = { id: 'ABC' };
        const dashboardLayoutCollection: IDashboardLayout[] = [{ id: 'CBA' }];
        expectedResult = service.addDashboardLayoutToCollectionIfMissing(dashboardLayoutCollection, dashboardLayout);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(dashboardLayout);
      });

      it('should add only unique DashboardLayout to an array', () => {
        const dashboardLayoutArray: IDashboardLayout[] = [{ id: 'ABC' }, { id: 'CBA' }, { id: '86899c1b-02bd-47f7-8262-7ef1dcf27c24' }];
        const dashboardLayoutCollection: IDashboardLayout[] = [{ id: 'ABC' }];
        expectedResult = service.addDashboardLayoutToCollectionIfMissing(dashboardLayoutCollection, ...dashboardLayoutArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const dashboardLayout: IDashboardLayout = { id: 'ABC' };
        const dashboardLayout2: IDashboardLayout = { id: 'CBA' };
        expectedResult = service.addDashboardLayoutToCollectionIfMissing([], dashboardLayout, dashboardLayout2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(dashboardLayout);
        expect(expectedResult).toContain(dashboardLayout2);
      });

      it('should accept null and undefined values', () => {
        const dashboardLayout: IDashboardLayout = { id: 'ABC' };
        expectedResult = service.addDashboardLayoutToCollectionIfMissing([], null, dashboardLayout, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(dashboardLayout);
      });

      it('should return initial array if no DashboardLayout is added', () => {
        const dashboardLayoutCollection: IDashboardLayout[] = [{ id: 'ABC' }];
        expectedResult = service.addDashboardLayoutToCollectionIfMissing(dashboardLayoutCollection, undefined, null);
        expect(expectedResult).toEqual(dashboardLayoutCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
