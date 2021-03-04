import { Moment } from 'moment';
import { IEmploye } from 'app/shared/model/employe.model';

export interface IEpoux {
  id?: number;
  prenom?: string;
  nom?: string;
  dateNaissance?: Moment;
  lieuNaissance?: string;
  employe?: IEmploye;
}

export class Epoux implements IEpoux {
  constructor(
    public id?: number,
    public prenom?: string,
    public nom?: string,
    public dateNaissance?: Moment,
    public lieuNaissance?: string,
    public employe?: IEmploye
  ) {}
}
