import { Moment } from 'moment';
import { IBanqueEmploye } from 'app/shared/model/banque-employe.model';
import { IContrat } from 'app/shared/model/contrat.model';
import { IEnfant } from 'app/shared/model/enfant.model';
import { IEpoux } from 'app/shared/model/epoux.model';
import { ISociete } from 'app/shared/model/societe.model';
import { IPoste } from 'app/shared/model/poste.model';
import { SituationMatrimoniale } from 'app/shared/model/enumerations/situation-matrimoniale.model';
import { Sexe } from 'app/shared/model/enumerations/sexe.model';

export interface IEmploye {
  id?: number;
  prenom?: string;
  nom?: string;
  dateNaissance?: Moment;
  lieuNaissance?: string;
  cni?: string;
  profession?: string;
  numeroCaisseSecurite?: string;
  numeroIpres?: string;
  matricule?: string;
  imageUrl?: string;
  prenomPere?: string;
  prenomMere?: string;
  nomMere?: string;
  situationMatrimoniale?: SituationMatrimoniale;
  sexe?: Sexe;
  trimf?: string;
  statut?: string;
  dateEmbauche?: Moment;
  nationalite?: string;
  compteBanquaire?: IBanqueEmploye;
  contrats?: IContrat[];
  enfants?: IEnfant[];
  epouxs?: IEpoux[];
  societe?: ISociete;
  poste?: IPoste;
}

export class Employe implements IEmploye {
  constructor(
    public id?: number,
    public prenom?: string,
    public nom?: string,
    public dateNaissance?: Moment,
    public lieuNaissance?: string,
    public cni?: string,
    public profession?: string,
    public numeroCaisseSecurite?: string,
    public numeroIpres?: string,
    public matricule?: string,
    public imageUrl?: string,
    public prenomPere?: string,
    public prenomMere?: string,
    public nomMere?: string,
    public situationMatrimoniale?: SituationMatrimoniale,
    public sexe?: Sexe,
    public trimf?: string,
    public statut?: string,
    public dateEmbauche?: Moment,
    public nationalite?: string,
    public compteBanquaire?: IBanqueEmploye,
    public contrats?: IContrat[],
    public enfants?: IEnfant[],
    public epouxs?: IEpoux[],
    public societe?: ISociete,
    public poste?: IPoste
  ) {}
}
