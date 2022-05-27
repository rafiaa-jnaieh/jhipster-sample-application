import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DashboardLayoutDetailComponent } from './dashboard-layout-detail.component';

describe('DashboardLayout Management Detail Component', () => {
  let comp: DashboardLayoutDetailComponent;
  let fixture: ComponentFixture<DashboardLayoutDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DashboardLayoutDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ dashboardLayout: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(DashboardLayoutDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DashboardLayoutDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load dashboardLayout on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.dashboardLayout).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
