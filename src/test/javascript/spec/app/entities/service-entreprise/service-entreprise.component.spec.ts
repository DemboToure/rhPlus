import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { RhPlusTestModule } from '../../../test.module';
import { ServiceEntrepriseComponent } from 'app/entities/service-entreprise/service-entreprise.component';
import { ServiceEntrepriseService } from 'app/entities/service-entreprise/service-entreprise.service';
import { ServiceEntreprise } from 'app/shared/model/service-entreprise.model';

describe('Component Tests', () => {
  describe('ServiceEntreprise Management Component', () => {
    let comp: ServiceEntrepriseComponent;
    let fixture: ComponentFixture<ServiceEntrepriseComponent>;
    let service: ServiceEntrepriseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [ServiceEntrepriseComponent],
      })
        .overrideTemplate(ServiceEntrepriseComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ServiceEntrepriseComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ServiceEntrepriseService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ServiceEntreprise(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.serviceEntreprises && comp.serviceEntreprises[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
