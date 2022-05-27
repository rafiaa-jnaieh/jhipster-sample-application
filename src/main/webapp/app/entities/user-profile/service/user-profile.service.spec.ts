import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IUserProfile, UserProfile } from '../user-profile.model';

import { UserProfileService } from './user-profile.service';

describe('UserProfile Service', () => {
  let service: UserProfileService;
  let httpMock: HttpTestingController;
  let elemDefault: IUserProfile;
  let expectedResult: IUserProfile | IUserProfile[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(UserProfileService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 'AAAAAAA',
      name: 'AAAAAAA',
      priority: 0,
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

    it('should create a UserProfile', () => {
      const returnedFromService = Object.assign(
        {
          id: 'ID',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new UserProfile()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a UserProfile', () => {
      const returnedFromService = Object.assign(
        {
          id: 'BBBBBB',
          name: 'BBBBBB',
          priority: 1,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a UserProfile', () => {
      const patchObject = Object.assign(
        {
          name: 'BBBBBB',
        },
        new UserProfile()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of UserProfile', () => {
      const returnedFromService = Object.assign(
        {
          id: 'BBBBBB',
          name: 'BBBBBB',
          priority: 1,
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

    it('should delete a UserProfile', () => {
      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addUserProfileToCollectionIfMissing', () => {
      it('should add a UserProfile to an empty array', () => {
        const userProfile: IUserProfile = { id: 'ABC' };
        expectedResult = service.addUserProfileToCollectionIfMissing([], userProfile);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userProfile);
      });

      it('should not add a UserProfile to an array that contains it', () => {
        const userProfile: IUserProfile = { id: 'ABC' };
        const userProfileCollection: IUserProfile[] = [
          {
            ...userProfile,
          },
          { id: 'CBA' },
        ];
        expectedResult = service.addUserProfileToCollectionIfMissing(userProfileCollection, userProfile);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a UserProfile to an array that doesn't contain it", () => {
        const userProfile: IUserProfile = { id: 'ABC' };
        const userProfileCollection: IUserProfile[] = [{ id: 'CBA' }];
        expectedResult = service.addUserProfileToCollectionIfMissing(userProfileCollection, userProfile);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userProfile);
      });

      it('should add only unique UserProfile to an array', () => {
        const userProfileArray: IUserProfile[] = [{ id: 'ABC' }, { id: 'CBA' }, { id: 'ac4abcc7-1eda-4791-95fa-50526180abe2' }];
        const userProfileCollection: IUserProfile[] = [{ id: 'ABC' }];
        expectedResult = service.addUserProfileToCollectionIfMissing(userProfileCollection, ...userProfileArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const userProfile: IUserProfile = { id: 'ABC' };
        const userProfile2: IUserProfile = { id: 'CBA' };
        expectedResult = service.addUserProfileToCollectionIfMissing([], userProfile, userProfile2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(userProfile);
        expect(expectedResult).toContain(userProfile2);
      });

      it('should accept null and undefined values', () => {
        const userProfile: IUserProfile = { id: 'ABC' };
        expectedResult = service.addUserProfileToCollectionIfMissing([], null, userProfile, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(userProfile);
      });

      it('should return initial array if no UserProfile is added', () => {
        const userProfileCollection: IUserProfile[] = [{ id: 'ABC' }];
        expectedResult = service.addUserProfileToCollectionIfMissing(userProfileCollection, undefined, null);
        expect(expectedResult).toEqual(userProfileCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
