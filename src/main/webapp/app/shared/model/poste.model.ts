import { IEmploye } from 'app/shared/model/employe.model';
import { IServiceEntreprise } from 'app/shared/model/service-entreprise.model';

export interface IPoste {
  id?: number;
  nom?: string;
  nombreRequis?: number;
  employes?: IEmploye[];
  serviceEntreprise?: IServiceEntreprise;
}

export class Poste implements IPoste {
  constructor(
    public id?: number,
    public nom?: string,
    public nombreRequis?: number,
    public employes?: IEmploye[],
    public serviceEntreprise?: IServiceEntreprise
  ) {}
}
