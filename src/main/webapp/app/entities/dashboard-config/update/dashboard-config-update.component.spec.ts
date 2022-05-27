import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DashboardConfigService } from '../service/dashboard-config.service';
import { IDashboardConfig, DashboardConfig } from '../dashboard-config.model';

import { DashboardConfigUpdateComponent } from './dashboard-config-update.component';

describe('DashboardConfig Management Update Component', () => {
  let comp: DashboardConfigUpdateComponent;
  let fixture: ComponentFixture<DashboardConfigUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let dashboardConfigService: DashboardConfigService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DashboardConfigUpdateComponent],
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
      .overrideTemplate(DashboardConfigUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DashboardConfigUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    dashboardConfigService = TestBed.inject(DashboardConfigService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const dashboardConfig: IDashboardConfig = { id: 'CBA' };

      activatedRoute.data = of({ dashboardConfig });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(dashboardConfig));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardConfig>>();
      const dashboardConfig = { id: 'ABC' };
      jest.spyOn(dashboardConfigService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardConfig });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dashboardConfig }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(dashboardConfigService.update).toHaveBeenCalledWith(dashboardConfig);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardConfig>>();
      const dashboardConfig = new DashboardConfig();
      jest.spyOn(dashboardConfigService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardConfig });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: dashboardConfig }));
      saveSubject.complete();

      // THEN
      expect(dashboardConfigService.create).toHaveBeenCalledWith(dashboardConfig);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DashboardConfig>>();
      const dashboardConfig = { id: 'ABC' };
      jest.spyOn(dashboardConfigService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ dashboardConfig });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(dashboardConfigService.update).toHaveBeenCalledWith(dashboardConfig);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
