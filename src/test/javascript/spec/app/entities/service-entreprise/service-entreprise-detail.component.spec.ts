import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RhPlusTestModule } from '../../../test.module';
import { ServiceEntrepriseDetailComponent } from 'app/entities/service-entreprise/service-entreprise-detail.component';
import { ServiceEntreprise } from 'app/shared/model/service-entreprise.model';

describe('Component Tests', () => {
  describe('ServiceEntreprise Management Detail Component', () => {
    let comp: ServiceEntrepriseDetailComponent;
    let fixture: ComponentFixture<ServiceEntrepriseDetailComponent>;
    const route = ({ data: of({ serviceEntreprise: new ServiceEntreprise(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [ServiceEntrepriseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ServiceEntrepriseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ServiceEntrepriseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load serviceEntreprise on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.serviceEntreprise).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
