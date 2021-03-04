import { IPoste } from 'app/shared/model/poste.model';

export interface IServiceEntreprise {
  id?: number;
  nom?: string;
  adresse?: string;
  tel?: string;
  responsable?: number;
  postes?: IPoste[];
}

export class ServiceEntreprise implements IServiceEntreprise {
  constructor(
    public id?: number,
    public nom?: string,
    public adresse?: string,
    public tel?: string,
    public responsable?: number,
    public postes?: IPoste[]
  ) {}
}
