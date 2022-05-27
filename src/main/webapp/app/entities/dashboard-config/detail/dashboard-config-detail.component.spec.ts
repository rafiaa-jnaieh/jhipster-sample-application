import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DashboardConfigDetailComponent } from './dashboard-config-detail.component';

describe('DashboardConfig Management Detail Component', () => {
  let comp: DashboardConfigDetailComponent;
  let fixture: ComponentFixture<DashboardConfigDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DashboardConfigDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ dashboardConfig: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(DashboardConfigDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DashboardConfigDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load dashboardConfig on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.dashboardConfig).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
