import { Moment } from 'moment';
import { IEmploye } from 'app/shared/model/employe.model';

export interface IEnfant {
  id?: number;
  prenom?: string;
  nom?: string;
  dateNaissance?: Moment;
  lieuNaissance?: string;
  employe?: IEmploye;
}

export class Enfant implements IEnfant {
  constructor(
    public id?: number,
    public prenom?: string,
    public nom?: string,
    public dateNaissance?: Moment,
    public lieuNaissance?: string,
    public employe?: IEmploye
  ) {}
}
