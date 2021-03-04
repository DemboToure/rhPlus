import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RhPlusTestModule } from '../../../test.module';
import { ServiceEntrepriseUpdateComponent } from 'app/entities/service-entreprise/service-entreprise-update.component';
import { ServiceEntrepriseService } from 'app/entities/service-entreprise/service-entreprise.service';
import { ServiceEntreprise } from 'app/shared/model/service-entreprise.model';

describe('Component Tests', () => {
  describe('ServiceEntreprise Management Update Component', () => {
    let comp: ServiceEntrepriseUpdateComponent;
    let fixture: ComponentFixture<ServiceEntrepriseUpdateComponent>;
    let service: ServiceEntrepriseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [ServiceEntrepriseUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ServiceEntrepriseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ServiceEntrepriseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ServiceEntrepriseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ServiceEntreprise(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ServiceEntreprise();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
