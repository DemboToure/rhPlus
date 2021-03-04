import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { EmployeService } from 'app/entities/employe/employe.service';
import { IEmploye, Employe } from 'app/shared/model/employe.model';
import { SituationMatrimoniale } from 'app/shared/model/enumerations/situation-matrimoniale.model';
import { Sexe } from 'app/shared/model/enumerations/sexe.model';

describe('Service Tests', () => {
  describe('Employe Service', () => {
    let injector: TestBed;
    let service: EmployeService;
    let httpMock: HttpTestingController;
    let elemDefault: IEmploye;
    let expectedResult: IEmploye | IEmploye[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EmployeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Employe(
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        SituationMatrimoniale.MARIER,
        Sexe.FEMME,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateNaissance: currentDate.format(DATE_FORMAT),
            dateEmbauche: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Employe', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateNaissance: currentDate.format(DATE_FORMAT),
            dateEmbauche: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
            dateEmbauche: currentDate,
          },
          returnedFromService
        );

        service.create(new Employe()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Employe', () => {
        const returnedFromService = Object.assign(
          {
            prenom: 'BBBBBB',
            nom: 'BBBBBB',
            dateNaissance: currentDate.format(DATE_FORMAT),
            lieuNaissance: 'BBBBBB',
            cni: 'BBBBBB',
            profession: 'BBBBBB',
            numeroCaisseSecurite: 'BBBBBB',
            numeroIpres: 'BBBBBB',
            matricule: 'BBBBBB',
            imageUrl: 'BBBBBB',
            prenomPere: 'BBBBBB',
            prenomMere: 'BBBBBB',
            nomMere: 'BBBBBB',
            situationMatrimoniale: 'BBBBBB',
            sexe: 'BBBBBB',
            trimf: 'BBBBBB',
            statut: 'BBBBBB',
            dateEmbauche: currentDate.format(DATE_FORMAT),
            nationalite: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
            dateEmbauche: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Employe', () => {
        const returnedFromService = Object.assign(
          {
            prenom: 'BBBBBB',
            nom: 'BBBBBB',
            dateNaissance: currentDate.format(DATE_FORMAT),
            lieuNaissance: 'BBBBBB',
            cni: 'BBBBBB',
            profession: 'BBBBBB',
            numeroCaisseSecurite: 'BBBBBB',
            numeroIpres: 'BBBBBB',
            matricule: 'BBBBBB',
            imageUrl: 'BBBBBB',
            prenomPere: 'BBBBBB',
            prenomMere: 'BBBBBB',
            nomMere: 'BBBBBB',
            situationMatrimoniale: 'BBBBBB',
            sexe: 'BBBBBB',
            trimf: 'BBBBBB',
            statut: 'BBBBBB',
            dateEmbauche: currentDate.format(DATE_FORMAT),
            nationalite: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateNaissance: currentDate,
            dateEmbauche: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Employe', () => {
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
