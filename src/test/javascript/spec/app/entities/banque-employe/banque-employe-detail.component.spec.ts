import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RhPlusTestModule } from '../../../test.module';
import { BanqueEmployeDetailComponent } from 'app/entities/banque-employe/banque-employe-detail.component';
import { BanqueEmploye } from 'app/shared/model/banque-employe.model';

describe('Component Tests', () => {
  describe('BanqueEmploye Management Detail Component', () => {
    let comp: BanqueEmployeDetailComponent;
    let fixture: ComponentFixture<BanqueEmployeDetailComponent>;
    const route = ({ data: of({ banqueEmploye: new BanqueEmploye(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [BanqueEmployeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(BanqueEmployeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BanqueEmployeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load banqueEmploye on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.banqueEmploye).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
