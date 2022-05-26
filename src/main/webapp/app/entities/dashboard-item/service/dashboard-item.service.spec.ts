import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDashboardItem, DashboardItem } from '../dashboard-item.model';

import { DashboardItemService } from './dashboard-item.service';

describe('DashboardItem Service', () => {
  let service: DashboardItemService;
  let httpMock: HttpTestingController;
  let elemDefault: IDashboardItem;
  let expectedResult: IDashboardItem | IDashboardItem[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DashboardItemService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 'AAAAAAA',
      cols: 0,
      rows: 0,
      x: 0,
      y: 0,
      content: 'AAAAAAA',
      city: 'AAAAAAA',
      stateProvince: 'AAAAAAA',
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

    it('should create a DashboardItem', () => {
      const returnedFromService = Object.assign(
        {
          id: 'ID',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new DashboardItem()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DashboardItem', () => {
      const returnedFromService = Object.assign(
        {
          id: 'BBBBBB',
          cols: 1,
          rows: 1,
          x: 1,
          y: 1,
          content: 'BBBBBB',
          city: 'BBBBBB',
          stateProvince: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DashboardItem', () => {
      const patchObject = Object.assign(
        {
          content: 'BBBBBB',
          city: 'BBBBBB',
          stateProvince: 'BBBBBB',
        },
        new DashboardItem()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DashboardItem', () => {
      const returnedFromService = Object.assign(
        {
          id: 'BBBBBB',
          cols: 1,
          rows: 1,
          x: 1,
          y: 1,
          content: 'BBBBBB',
          city: 'BBBBBB',
          stateProvince: 'BBBBBB',
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

    it('should delete a DashboardItem', () => {
      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDashboardItemToCollectionIfMissing', () => {
      it('should add a DashboardItem to an empty array', () => {
        const dashboardItem: IDashboardItem = { id: 'ABC' };
        expectedResult = service.addDashboardItemToCollectionIfMissing([], dashboardItem);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(dashboardItem);
      });

      it('should not add a DashboardItem to an array that contains it', () => {
        const dashboardItem: IDashboardItem = { id: 'ABC' };
        const dashboardItemCollection: IDashboardItem[] = [
          {
            ...dashboardItem,
          },
          { id: 'CBA' },
        ];
        expectedResult = service.addDashboardItemToCollectionIfMissing(dashboardItemCollection, dashboardItem);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DashboardItem to an array that doesn't contain it", () => {
        const dashboardItem: IDashboardItem = { id: 'ABC' };
        const dashboardItemCollection: IDashboardItem[] = [{ id: 'CBA' }];
        expectedResult = service.addDashboardItemToCollectionIfMissing(dashboardItemCollection, dashboardItem);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(dashboardItem);
      });

      it('should add only unique DashboardItem to an array', () => {
        const dashboardItemArray: IDashboardItem[] = [{ id: 'ABC' }, { id: 'CBA' }, { id: '25dbac4f-bec1-43ed-9a4a-c6563fa1faf0' }];
        const dashboardItemCollection: IDashboardItem[] = [{ id: 'ABC' }];
        expectedResult = service.addDashboardItemToCollectionIfMissing(dashboardItemCollection, ...dashboardItemArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const dashboardItem: IDashboardItem = { id: 'ABC' };
        const dashboardItem2: IDashboardItem = { id: 'CBA' };
        expectedResult = service.addDashboardItemToCollectionIfMissing([], dashboardItem, dashboardItem2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(dashboardItem);
        expect(expectedResult).toContain(dashboardItem2);
      });

      it('should accept null and undefined values', () => {
        const dashboardItem: IDashboardItem = { id: 'ABC' };
        expectedResult = service.addDashboardItemToCollectionIfMissing([], null, dashboardItem, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(dashboardItem);
      });

      it('should return initial array if no DashboardItem is added', () => {
        const dashboardItemCollection: IDashboardItem[] = [{ id: 'ABC' }];
        expectedResult = service.addDashboardItemToCollectionIfMissing(dashboardItemCollection, undefined, null);
        expect(expectedResult).toEqual(dashboardItemCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
