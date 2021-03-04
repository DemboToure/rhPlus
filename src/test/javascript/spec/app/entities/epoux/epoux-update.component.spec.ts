import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RhPlusTestModule } from '../../../test.module';
import { EpouxUpdateComponent } from 'app/entities/epoux/epoux-update.component';
import { EpouxService } from 'app/entities/epoux/epoux.service';
import { Epoux } from 'app/shared/model/epoux.model';

describe('Component Tests', () => {
  describe('Epoux Management Update Component', () => {
    let comp: EpouxUpdateComponent;
    let fixture: ComponentFixture<EpouxUpdateComponent>;
    let service: EpouxService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [EpouxUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EpouxUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EpouxUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EpouxService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Epoux(123);
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
        const entity = new Epoux();
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
