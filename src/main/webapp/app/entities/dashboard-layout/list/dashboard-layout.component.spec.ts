import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { DashboardLayoutService } from '../service/dashboard-layout.service';

import { DashboardLayoutComponent } from './dashboard-layout.component';

describe('DashboardLayout Management Component', () => {
  let comp: DashboardLayoutComponent;
  let fixture: ComponentFixture<DashboardLayoutComponent>;
  let service: DashboardLayoutService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [DashboardLayoutComponent],
    })
      .overrideTemplate(DashboardLayoutComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DashboardLayoutComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DashboardLayoutService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 'ABC' }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.dashboardLayouts?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });
});
