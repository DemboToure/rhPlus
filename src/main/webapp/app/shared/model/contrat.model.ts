import { Moment } from 'moment';
import { IEmploye } from 'app/shared/model/employe.model';

export interface IContrat {
  id?: number;
  dateDebut?: Moment;
  dateFin?: Moment;
  salaire?: number;
  document?: string;
  employe?: IEmploye;
}

export class Contrat implements IContrat {
  constructor(
    public id?: number,
    public dateDebut?: Moment,
    public dateFin?: Moment,
    public salaire?: number,
    public document?: string,
    public employe?: IEmploye
  ) {}
}
