import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DashboardItemService } from '../service/dashboard-item.service';
import { IDashboardItem, DashboardItem } from '../dashboard-item.model';
import { IUserProfile } from 'app/entities/user-profile/user-profile.model';
import { UserProfileService } from 'app/entities/user-profile/service/user-profile.service';

import { DashboardItemUpdateComponent } from './dashboard-item-update.component';

describe('DashboardItem Management Update Component', () => {
  let comp: DashboardItemUpdateComponent;
  let fixture: ComponentFixture<DashboardItemUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let dashboardItemService: DashboardItemService;
  let userProfileService: UserProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DashboardItemUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DashboardItemUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DashboardItemUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    dashboardItemService = TestBed.inject(DashboardItemService);
    userProfileService = TestBed.inject(UserProfileService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call UserProfile query and add missing value', () => {
      const dashboardItem: IDashboardItem = { id: 'CBA' };
      const userProfile: IUserProfile = { id: '3e957947-713d-4b87-babe-8845f13b0a9d' };
      dashboardItem.userProfile = userProfile;

      const userProfileCollection: IUserProfile[] = [{ id: '52ead5fd-2cd0-4d71-8530-a7dcffce00f4' }];
      jest.spyOn(userProfileService, 'query').mockReturnValue(of(new HttpResponse({ body: userProfileCollection })));
      const additionalUserProfiles = [userProfile];
      const expectedCollection: IUserProfile[] = [...additionalUserProfiles, ...userProfileCollection];
      jest.spyOn(userProfileService, 'addUserProfileToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      expect(userProfileService.query).toHaveBeenCalled();
      expect(userProfileService.addUserProfileToCollectionIfMissing).toHaveBeenCalledWith(userProfileCollection, ...additionalUserProfiles);
      expect(comp.userProfilesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const dashboardItem: IDashboardItem = { id: 'CBA' };
      const userProfile: IUserProfile = { id: '8b2f27bd-9729-4333-a52b-6c90e6fa5e68' };
      dashboardItem.userProfile = userProfile;

      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(dashboardItem));
      expect(comp.userProfilesSharedCollection).toContain(userProfile);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardItem>>();
      const dashboardItem = { id: 'ABC' };
      jest.spyOn(dashboardItemService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dashboardItem }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(dashboardItemService.update).toHaveBeenCalledWith(dashboardItem);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardItem>>();
      const dashboardItem = new DashboardItem();
      jest.spyOn(dashboardItemService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dashboardItem }));
      saveSubject.complete();

      // THEN
      expect(dashboardItemService.create).toHaveBeenCalledWith(dashboardItem);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardItem>>();
      const dashboardItem = { id: 'ABC' };
      jest.spyOn(dashboardItemService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(dashboardItemService.update).toHaveBeenCalledWith(dashboardItem);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackUserProfileById', () => {
      it('Should return tracked UserProfile primary key', () => {
        const entity = { id: 'ABC' };
        const trackResult = comp.trackUserProfileById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
