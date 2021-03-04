import { IEmploye } from 'app/shared/model/employe.model';

export interface ISociete {
  id?: number;
  nom?: string;
  adresse?: string;
  telephone?: string;
  fixe?: string;
  ninea?: string;
  logoUrl?: string;
  employes?: IEmploye[];
}

export class Societe implements ISociete {
  constructor(
    public id?: number,
    public nom?: string,
    public adresse?: string,
    public telephone?: string,
    public fixe?: string,
    public ninea?: string,
    public logoUrl?: string,
    public employes?: IEmploye[]
  ) {}
}
