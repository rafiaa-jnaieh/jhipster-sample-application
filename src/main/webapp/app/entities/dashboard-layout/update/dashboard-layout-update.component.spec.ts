import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DashboardLayoutService } from '../service/dashboard-layout.service';
import { IDashboardLayout, DashboardLayout } from '../dashboard-layout.model';
import { IUserProfile } from 'app/entities/user-profile/user-profile.model';
import { UserProfileService } from 'app/entities/user-profile/service/user-profile.service';

import { DashboardLayoutUpdateComponent } from './dashboard-layout-update.component';

describe('DashboardLayout Management Update Component', () => {
  let comp: DashboardLayoutUpdateComponent;
  let fixture: ComponentFixture<DashboardLayoutUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let dashboardLayoutService: DashboardLayoutService;
  let userProfileService: UserProfileService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DashboardLayoutUpdateComponent],
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
      .overrideTemplate(DashboardLayoutUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DashboardLayoutUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    dashboardLayoutService = TestBed.inject(DashboardLayoutService);
    userProfileService = TestBed.inject(UserProfileService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call UserProfile query and add missing value', () => {
      const dashboardLayout: IDashboardLayout = { id: 'CBA' };
      const userProfile: IUserProfile = { id: '26c53c80-9a33-4efb-87c6-646a3a3fc07e' };
      dashboardLayout.userProfile = userProfile;

      const userProfileCollection: IUserProfile[] = [{ id: '0587a4df-168a-41ac-b4e9-5af7e45ef651' }];
      jest.spyOn(userProfileService, 'query').mockReturnValue(of(new HttpResponse({ body: userProfileCollection })));
      const additionalUserProfiles = [userProfile];
      const expectedCollection: IUserProfile[] = [...additionalUserProfiles, ...userProfileCollection];
      jest.spyOn(userProfileService, 'addUserProfileToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ dashboardLayout });
      comp.ngOnInit();

      expect(userProfileService.query).toHaveBeenCalled();
      expect(userProfileService.addUserProfileToCollectionIfMissing).toHaveBeenCalledWith(userProfileCollection, ...additionalUserProfiles);
      expect(comp.userProfilesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const dashboardLayout: IDashboardLayout = { id: 'CBA' };
      const userProfile: IUserProfile = { id: '31492994-172a-4ace-8d8c-e38e3ede5ddb' };
      dashboardLayout.userProfile = userProfile;

      activatedRoute.data = of({ dashboardLayout });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(dashboardLayout));
      expect(comp.userProfilesSharedCollection).toContain(userProfile);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardLayout>>();
      const dashboardLayout = { id: 'ABC' };
      jest.spyOn(dashboardLayoutService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardLayout });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dashboardLayout }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(dashboardLayoutService.update).toHaveBeenCalledWith(dashboardLayout);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardLayout>>();
      const dashboardLayout = new DashboardLayout();
      jest.spyOn(dashboardLayoutService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardLayout });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dashboardLayout }));
      saveSubject.complete();

      // THEN
      expect(dashboardLayoutService.create).toHaveBeenCalledWith(dashboardLayout);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardLayout>>();
      const dashboardLayout = { id: 'ABC' };
      jest.spyOn(dashboardLayoutService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardLayout });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(dashboardLayoutService.update).toHaveBeenCalledWith(dashboardLayout);
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
