import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DashboardLayoutService } from '../service/dashboard-layout.service';
import { IDashboardLayout, DashboardLayout } from '../dashboard-layout.model';

import { DashboardLayoutUpdateComponent } from './dashboard-layout-update.component';

describe('DashboardLayout Management Update Component', () => {
  let comp: DashboardLayoutUpdateComponent;
  let fixture: ComponentFixture<DashboardLayoutUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let dashboardLayoutService: DashboardLayoutService;

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

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const dashboardLayout: IDashboardLayout = { id: 'CBA' };

      activatedRoute.data = of({ dashboardLayout });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(dashboardLayout));
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
});
