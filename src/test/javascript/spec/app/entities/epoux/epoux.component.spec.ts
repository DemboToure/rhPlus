import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RhPlusTestModule } from '../../../test.module';
import { EpouxComponent } from 'app/entities/epoux/epoux.component';
import { EpouxService } from 'app/entities/epoux/epoux.service';
import { Epoux } from 'app/shared/model/epoux.model';

describe('Component Tests', () => {
  describe('Epoux Management Component', () => {
    let comp: EpouxComponent;
    let fixture: ComponentFixture<EpouxComponent>;
    let service: EpouxService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [EpouxComponent],
      })
        .overrideTemplate(EpouxComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EpouxComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EpouxService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Epoux(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.epouxes && comp.epouxes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
