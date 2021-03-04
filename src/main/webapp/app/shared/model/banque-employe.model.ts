import { IEmploye } from 'app/shared/model/employe.model';
import { IBanque } from 'app/shared/model/banque.model';

export interface IBanqueEmploye {
  id?: number;
  nom?: string;
  numeroCompte?: string;
  codeBanque?: string;
  codeGuichet?: string;
  cleRib?: string;
  pdfDomiciliation?: string;
  employe?: IEmploye;
  banque?: IBanque;
}

export class BanqueEmploye implements IBanqueEmploye {
  constructor(
    public id?: number,
    public nom?: string,
    public numeroCompte?: string,
    public codeBanque?: string,
    public codeGuichet?: string,
    public cleRib?: string,
    public pdfDomiciliation?: string,
    public employe?: IEmploye,
    public banque?: IBanque
  ) {}
}
