import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RhPlusTestModule } from '../../../test.module';
import { EnfantComponent } from 'app/entities/enfant/enfant.component';
import { EnfantService } from 'app/entities/enfant/enfant.service';
import { Enfant } from 'app/shared/model/enfant.model';

describe('Component Tests', () => {
  describe('Enfant Management Component', () => {
    let comp: EnfantComponent;
    let fixture: ComponentFixture<EnfantComponent>;
    let service: EnfantService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [EnfantComponent],
      })
        .overrideTemplate(EnfantComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EnfantComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EnfantService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Enfant(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.enfants && comp.enfants[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
