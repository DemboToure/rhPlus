import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RhPlusTestModule } from '../../../test.module';
import { EpouxDetailComponent } from 'app/entities/epoux/epoux-detail.component';
import { Epoux } from 'app/shared/model/epoux.model';

describe('Component Tests', () => {
  describe('Epoux Management Detail Component', () => {
    let comp: EpouxDetailComponent;
    let fixture: ComponentFixture<EpouxDetailComponent>;
    const route = ({ data: of({ epoux: new Epoux(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [RhPlusTestModule],
        declarations: [EpouxDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EpouxDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EpouxDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load epoux on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.epoux).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
