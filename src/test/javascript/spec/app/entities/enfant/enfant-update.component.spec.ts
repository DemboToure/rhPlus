import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RhPlusTestModule } from '../../../test.module';
import { EnfantUpdateComponent } from 'app/entities/enfant/enfant-update.component';
import { EnfantService } from 'app/entities/enfant/enfant.service';
import { Enfant } from 'app/shared/model/enfant.model';

describe('Component Tests', () => {
  describe('Enfant Management Update Component', () => {
    let comp: EnfantUpdateComponent;
    let fixture: ComponentFixture<EnfantUpdateComponent>;
    let service: EnfantService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [EnfantUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EnfantUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EnfantUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EnfantService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Enfant(123);
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
        const entity = new Enfant();
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
