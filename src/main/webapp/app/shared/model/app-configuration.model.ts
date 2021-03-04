export interface IAppConfiguration {
  id?: number;
  nomEntreprise?: string;
  adresse?: string;
  telephone?: string;
  fixe?: string;
  ninea?: string;
  logoUrl?: string;
}

export class AppConfiguration implements IAppConfiguration {
  constructor(
    public id?: number,
    public nomEntreprise?: string,
    public adresse?: string,
    public telephone?: string,
    public fixe?: string,
    public ninea?: string,
    public logoUrl?: string
  ) {}
}
