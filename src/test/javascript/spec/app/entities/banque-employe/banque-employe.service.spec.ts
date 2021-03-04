import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { BanqueEmployeService } from 'app/entities/banque-employe/banque-employe.service';
import { IBanqueEmploye, BanqueEmploye } from 'app/shared/model/banque-employe.model';

describe('Service Tests', () => {
  describe('BanqueEmploye Service', () => {
    let injector: TestBed;
    let service: BanqueEmployeService;
    let httpMock: HttpTestingController;
    let elemDefault: IBanqueEmploye;
    let expectedResult: IBanqueEmploye | IBanqueEmploye[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BanqueEmployeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new BanqueEmploye(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BanqueEmploye', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new BanqueEmploye()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BanqueEmploye', () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            numeroCompte: 'BBBBBB',
            codeBanque: 'BBBBBB',
            codeGuichet: 'BBBBBB',
            cleRib: 'BBBBBB',
            pdfDomiciliation: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BanqueEmploye', () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            numeroCompte: 'BBBBBB',
            codeBanque: 'BBBBBB',
            codeGuichet: 'BBBBBB',
            cleRib: 'BBBBBB',
            pdfDomiciliation: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BanqueEmploye', () => {
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
