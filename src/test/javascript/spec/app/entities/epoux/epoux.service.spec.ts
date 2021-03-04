import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { EpouxService } from 'app/entities/epoux/epoux.service';
import { IEpoux, Epoux } from 'app/shared/model/epoux.model';

describe('Service Tests', () => {
  describe('Epoux Service', () => {
    let injector: TestBed;
    let service: EpouxService;
    let httpMock: HttpTestingController;
    let elemDefault: IEpoux;
    let expectedResult: IEpoux | IEpoux[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EpouxService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Epoux(0, 'AAAAAAA', 'AAAAAAA', currentDate, 'AAAAAAA');
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

      it('should create a Epoux', () => {
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

        service.create(new Epoux()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Epoux', () => {
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

      it('should return a list of Epoux', () => {
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

      it('should delete a Epoux', () => {
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
