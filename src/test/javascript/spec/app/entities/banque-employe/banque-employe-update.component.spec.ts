import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { RhPlusTestModule } from '../../../test.module';
import { BanqueEmployeUpdateComponent } from 'app/entities/banque-employe/banque-employe-update.component';
import { BanqueEmployeService } from 'app/entities/banque-employe/banque-employe.service';
import { BanqueEmploye } from 'app/shared/model/banque-employe.model';

describe('Component Tests', () => {
  describe('BanqueEmploye Management Update Component', () => {
    let comp: BanqueEmployeUpdateComponent;
    let fixture: ComponentFixture<BanqueEmployeUpdateComponent>;
    let service: BanqueEmployeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [BanqueEmployeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BanqueEmployeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BanqueEmployeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BanqueEmployeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BanqueEmploye(123);
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
        const entity = new BanqueEmploye();
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
