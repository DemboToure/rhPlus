import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RhPlusTestModule } from '../../../test.module';
import { EnfantDetailComponent } from 'app/entities/enfant/enfant-detail.component';
import { Enfant } from 'app/shared/model/enfant.model';

describe('Component Tests', () => {
  describe('Enfant Management Detail Component', () => {
    let comp: EnfantDetailComponent;
    let fixture: ComponentFixture<EnfantDetailComponent>;
    const route = ({ data: of({ enfant: new Enfant(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [EnfantDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EnfantDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EnfantDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load enfant on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.enfant).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
