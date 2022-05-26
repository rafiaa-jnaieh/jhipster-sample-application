import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DashboardItemService } from '../service/dashboard-item.service';
import { IDashboardItem, DashboardItem } from '../dashboard-item.model';

import { DashboardItemUpdateComponent } from './dashboard-item-update.component';

describe('DashboardItem Management Update Component', () => {
  let comp: DashboardItemUpdateComponent;
  let fixture: ComponentFixture<DashboardItemUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let dashboardItemService: DashboardItemService;

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

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const dashboardItem: IDashboardItem = { id: 'CBA' };

      activatedRoute.data = of({ dashboardItem });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(dashboardItem));
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
});
