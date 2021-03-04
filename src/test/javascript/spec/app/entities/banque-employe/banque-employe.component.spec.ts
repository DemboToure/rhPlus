import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RhPlusTestModule } from '../../../test.module';
import { BanqueEmployeComponent } from 'app/entities/banque-employe/banque-employe.component';
import { BanqueEmployeService } from 'app/entities/banque-employe/banque-employe.service';
import { BanqueEmploye } from 'app/shared/model/banque-employe.model';

describe('Component Tests', () => {
  describe('BanqueEmploye Management Component', () => {
    let comp: BanqueEmployeComponent;
    let fixture: ComponentFixture<BanqueEmployeComponent>;
    let service: BanqueEmployeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [BanqueEmployeComponent],
      })
        .overrideTemplate(BanqueEmployeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BanqueEmployeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BanqueEmployeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new BanqueEmploye(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.banqueEmployes && comp.banqueEmployes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
