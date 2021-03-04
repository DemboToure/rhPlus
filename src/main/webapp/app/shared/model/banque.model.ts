import { IBanqueEmploye } from 'app/shared/model/banque-employe.model';

export interface IBanque {
  id?: number;
  nom?: string;
  numeroCompte?: string;
  codeBanque?: string;
  codeGuichet?: string;
  cleRib?: string;
  compteEmployes?: IBanqueEmploye[];
}

export class Banque implements IBanque {
  constructor(
    public id?: number,
    public nom?: string,
    public numeroCompte?: string,
    public codeBanque?: string,
    public codeGuichet?: string,
    public cleRib?: string,
    public compteEmployes?: IBanqueEmploye[]
  ) {}
}
