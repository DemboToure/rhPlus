import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ServiceEntrepriseService } from 'app/entities/service-entreprise/service-entreprise.service';
import { IServiceEntreprise, ServiceEntreprise } from 'app/shared/model/service-entreprise.model';

describe('Service Tests', () => {
  describe('ServiceEntreprise Service', () => {
    let injector: TestBed;
    let service: ServiceEntrepriseService;
    let httpMock: HttpTestingController;
    let elemDefault: IServiceEntreprise;
    let expectedResult: IServiceEntreprise | IServiceEntreprise[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ServiceEntrepriseService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ServiceEntreprise(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ServiceEntreprise', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ServiceEntreprise()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ServiceEntreprise', () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            adresse: 'BBBBBB',
            tel: 'BBBBBB',
            responsable: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ServiceEntreprise', () => {
        const returnedFromService = Object.assign(
          {
            nom: 'BBBBBB',
            adresse: 'BBBBBB',
            tel: 'BBBBBB',
            responsable: 1,
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

      it('should delete a ServiceEntreprise', () => {
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
