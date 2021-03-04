import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { EnfantService } from 'app/entities/enfant/enfant.service';
import { IEnfant, Enfant } from 'app/shared/model/enfant.model';

describe('Service Tests', () => {
  describe('Enfant Service', () => {
    let injector: TestBed;
    let service: EnfantService;
    let httpMock: HttpTestingController;
    let elemDefault: IEnfant;
    let expectedResult: IEnfant | IEnfant[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EnfantService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Enfant(0, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateNaissance: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Enfant', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateNaissance: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
          },
          returnedFromService
        );

        service.create(new Enfant()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Enfant', () => {
        const returnedFromService = Object.assign(
          {
            prenom: 'BBBBBB',
            nom: 'BBBBBB',
            dateNaissance: currentDate.format(DATE_FORMAT),
            lieuNaissance: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Enfant', () => {
        const returnedFromService = Object.assign(
          {
            prenom: 'BBBBBB',
            nom: 'BBBBBB',
            dateNaissance: currentDate.format(DATE_FORMAT),
            lieuNaissance: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Enfant', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
