import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { UserProfileService } from '../service/user-profile.service';
import { IUserProfile, UserProfile } from '../user-profile.model';
import { IDashboardLayout } from 'app/entities/dashboard-layout/dashboard-layout.model';
import { DashboardLayoutService } from 'app/entities/dashboard-layout/service/dashboard-layout.service';
import { IDashboardConfig } from 'app/entities/dashboard-config/dashboard-config.model';
import { DashboardConfigService } from 'app/entities/dashboard-config/service/dashboard-config.service';

import { UserProfileUpdateComponent } from './user-profile-update.component';

describe('UserProfile Management Update Component', () => {
  let comp: UserProfileUpdateComponent;
  let fixture: ComponentFixture<UserProfileUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let userProfileService: UserProfileService;
  let dashboardLayoutService: DashboardLayoutService;
  let dashboardConfigService: DashboardConfigService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [UserProfileUpdateComponent],
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
      .overrideTemplate(UserProfileUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(UserProfileUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    userProfileService = TestBed.inject(UserProfileService);
    dashboardLayoutService = TestBed.inject(DashboardLayoutService);
    dashboardConfigService = TestBed.inject(DashboardConfigService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call DashboardLayout query and add missing value', () => {
      const userProfile: IUserProfile = { id: 'CBA' };
      const dashboardLayout: IDashboardLayout = { id: '4dc442a0-8080-477c-a73e-61e747bd2876' };
      userProfile.dashboardLayout = dashboardLayout;

      const dashboardLayoutCollection: IDashboardLayout[] = [{ id: '9b574962-de04-4a57-9f81-2b329dd213fb' }];
      jest.spyOn(dashboardLayoutService, 'query').mockReturnValue(of(new HttpResponse({ body: dashboardLayoutCollection })));
      const additionalDashboardLayouts = [dashboardLayout];
      const expectedCollection: IDashboardLayout[] = [...additionalDashboardLayouts, ...dashboardLayoutCollection];
      jest.spyOn(dashboardLayoutService, 'addDashboardLayoutToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ userProfile });
      comp.ngOnInit();

      expect(dashboardLayoutService.query).toHaveBeenCalled();
      expect(dashboardLayoutService.addDashboardLayoutToCollectionIfMissing).toHaveBeenCalledWith(
        dashboardLayoutCollection,
        ...additionalDashboardLayouts
      );
      expect(comp.dashboardLayoutsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call DashboardConfig query and add missing value', () => {
      const userProfile: IUserProfile = { id: 'CBA' };
      const dashboardConfig: IDashboardConfig = { id: '67b6c2aa-855d-432d-82b9-6be3a2e1d4d6' };
      userProfile.dashboardConfig = dashboardConfig;

      const dashboardConfigCollection: IDashboardConfig[] = [{ id: '4913e7fa-94c0-4d92-b836-bfa752e8ca2c' }];
      jest.spyOn(dashboardConfigService, 'query').mockReturnValue(of(new HttpResponse({ body: dashboardConfigCollection })));
      const additionalDashboardConfigs = [dashboardConfig];
      const expectedCollection: IDashboardConfig[] = [...additionalDashboardConfigs, ...dashboardConfigCollection];
      jest.spyOn(dashboardConfigService, 'addDashboardConfigToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ userProfile });
      comp.ngOnInit();

      expect(dashboardConfigService.query).toHaveBeenCalled();
      expect(dashboardConfigService.addDashboardConfigToCollectionIfMissing).toHaveBeenCalledWith(
        dashboardConfigCollection,
        ...additionalDashboardConfigs
      );
      expect(comp.dashboardConfigsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const userProfile: IUserProfile = { id: 'CBA' };
      const dashboardLayout: IDashboardLayout = { id: '1c68535c-8459-4e3c-a3e6-b46d93c6bd7f' };
      userProfile.dashboardLayout = dashboardLayout;
      const dashboardConfig: IDashboardConfig = { id: '5bef1750-a40f-49cc-af36-757278fa2acb' };
      userProfile.dashboardConfig = dashboardConfig;

      activatedRoute.data = of({ userProfile });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(userProfile));
      expect(comp.dashboardLayoutsSharedCollection).toContain(dashboardLayout);
      expect(comp.dashboardConfigsSharedCollection).toContain(dashboardConfig);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<UserProfile>>();
      const userProfile = { id: 'ABC' };
      jest.spyOn(userProfileService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userProfile });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userProfile }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(userProfileService.update).toHaveBeenCalledWith(userProfile);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<UserProfile>>();
      const userProfile = new UserProfile();
      jest.spyOn(userProfileService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userProfile });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: userProfile }));
      saveSubject.complete();

      // THEN
      expect(userProfileService.create).toHaveBeenCalledWith(userProfile);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<UserProfile>>();
      const userProfile = { id: 'ABC' };
      jest.spyOn(userProfileService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ userProfile });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(userProfileService.update).toHaveBeenCalledWith(userProfile);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackDashboardLayoutById', () => {
      it('Should return tracked DashboardLayout primary key', () => {
        const entity = { id: 'ABC' };
        const trackResult = comp.trackDashboardLayoutById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackDashboardConfigById', () => {
      it('Should return tracked DashboardConfig primary key', () => {
        const entity = { id: 'ABC' };
        const trackResult = comp.trackDashboardConfigById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
