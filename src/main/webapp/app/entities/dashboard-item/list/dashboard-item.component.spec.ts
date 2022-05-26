import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { DashboardItemService } from '../service/dashboard-item.service';

import { DashboardItemComponent } from './dashboard-item.component';

describe('DashboardItem Management Component', () => {
  let comp: DashboardItemComponent;
  let fixture: ComponentFixture<DashboardItemComponent>;
  let service: DashboardItemService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [DashboardItemComponent],
    })
      .overrideTemplate(DashboardItemComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DashboardItemComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DashboardItemService);

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
    expect(comp.dashboardItems?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });
});
