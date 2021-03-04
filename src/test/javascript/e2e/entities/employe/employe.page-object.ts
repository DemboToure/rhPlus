import { element, by, ElementFinder } from 'protractor';

export class EmployeComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-employe div table .btn-danger'));
  title = element.all(by.css('jhi-employe div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getText();
  }
}

export class EmployeUpdatePage {
  pageTitle = element(by.id('jhi-employe-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  prenomInput = element(by.id('field_prenom'));
  nomInput = element(by.id('field_nom'));
  dateNaissanceInput = element(by.id('field_dateNaissance'));
  lieuNaissanceInput = element(by.id('field_lieuNaissance'));
  cniInput = element(by.id('field_cni'));
  professionInput = element(by.id('field_profession'));
  numeroCaisseSecuriteInput = element(by.id('field_numeroCaisseSecurite'));
  numeroIpresInput = element(by.id('field_numeroIpres'));
  matriculeInput = element(by.id('field_matricule'));
  imageUrlInput = element(by.id('field_imageUrl'));
  prenomPereInput = element(by.id('field_prenomPere'));
  prenomMereInput = element(by.id('field_prenomMere'));
  nomMereInput = element(by.id('field_nomMere'));
  situationMatrimonialeSelect = element(by.id('field_situationMatrimoniale'));
  sexeSelect = element(by.id('field_sexe'));
  trimfInput = element(by.id('field_trimf'));
  statutInput = element(by.id('field_statut'));
  dateEmbaucheInput = element(by.id('field_dateEmbauche'));
  nationaliteInput = element(by.id('field_nationalite'));

  compteBanquaireSelect = element(by.id('field_compteBanquaire'));
  societeSelect = element(by.id('field_societe'));
  posteSelect = element(by.id('field_poste'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setPrenomInput(prenom: string): Promise<void> {
    await this.prenomInput.sendKeys(prenom);
  }

  async getPrenomInput(): Promise<string> {
    return await this.prenomInput.getAttribute('value');
  }

  async setNomInput(nom: string): Promise<void> {
    await this.nomInput.sendKeys(nom);
  }

  async getNomInput(): Promise<string> {
    return await this.nomInput.getAttribute('value');
  }

  async setDateNaissanceInput(dateNaissance: string): Promise<void> {
    await this.dateNaissanceInput.sendKeys(dateNaissance);
  }

  async getDateNaissanceInput(): Promise<string> {
    return await this.dateNaissanceInput.getAttribute('value');
  }

  async setLieuNaissanceInput(lieuNaissance: string): Promise<void> {
    await this.lieuNaissanceInput.sendKeys(lieuNaissance);
  }

  async getLieuNaissanceInput(): Promise<string> {
    return await this.lieuNaissanceInput.getAttribute('value');
  }

  async setCniInput(cni: string): Promise<void> {
    await this.cniInput.sendKeys(cni);
  }

  async getCniInput(): Promise<string> {
    return await this.cniInput.getAttribute('value');
  }

  async setProfessionInput(profession: string): Promise<void> {
    await this.professionInput.sendKeys(profession);
  }

  async getProfessionInput(): Promise<string> {
    return await this.professionInput.getAttribute('value');
  }

  async setNumeroCaisseSecuriteInput(numeroCaisseSecurite: string): Promise<void> {
    await this.numeroCaisseSecuriteInput.sendKeys(numeroCaisseSecurite);
  }

  async getNumeroCaisseSecuriteInput(): Promise<string> {
    return await this.numeroCaisseSecuriteInput.getAttribute('value');
  }

  async setNumeroIpresInput(numeroIpres: string): Promise<void> {
    await this.numeroIpresInput.sendKeys(numeroIpres);
  }

  async getNumeroIpresInput(): Promise<string> {
    return await this.numeroIpresInput.getAttribute('value');
  }

  async setMatriculeInput(matricule: string): Promise<void> {
    await this.matriculeInput.sendKeys(matricule);
  }

  async getMatriculeInput(): Promise<string> {
    return await this.matriculeInput.getAttribute('value');
  }

  async setImageUrlInput(imageUrl: string): Promise<void> {
    await this.imageUrlInput.sendKeys(imageUrl);
  }

  async getImageUrlInput(): Promise<string> {
    return await this.imageUrlInput.getAttribute('value');
  }

  async setPrenomPereInput(prenomPere: string): Promise<void> {
    await this.prenomPereInput.sendKeys(prenomPere);
  }

  async getPrenomPereInput(): Promise<string> {
    return await this.prenomPereInput.getAttribute('value');
  }

  async setPrenomMereInput(prenomMere: string): Promise<void> {
    await this.prenomMereInput.sendKeys(prenomMere);
  }

  async getPrenomMereInput(): Promise<string> {
    return await this.prenomMereInput.getAttribute('value');
  }

  async setNomMereInput(nomMere: string): Promise<void> {
    await this.nomMereInput.sendKeys(nomMere);
  }

  async getNomMereInput(): Promise<string> {
    return await this.nomMereInput.getAttribute('value');
  }

  async setSituationMatrimonialeSelect(situationMatrimoniale: string): Promise<void> {
    await this.situationMatrimonialeSelect.sendKeys(situationMatrimoniale);
  }

  async getSituationMatrimonialeSelect(): Promise<string> {
    return await this.situationMatrimonialeSelect.element(by.css('option:checked')).getText();
  }

  async situationMatrimonialeSelectLastOption(): Promise<void> {
    await this.situationMatrimonialeSelect.all(by.tagName('option')).last().click();
  }

  async setSexeSelect(sexe: string): Promise<void> {
    await this.sexeSelect.sendKeys(sexe);
  }

  async getSexeSelect(): Promise<string> {
    return await this.sexeSelect.element(by.css('option:checked')).getText();
  }

  async sexeSelectLastOption(): Promise<void> {
    await this.sexeSelect.all(by.tagName('option')).last().click();
  }

  async setTrimfInput(trimf: string): Promise<void> {
    await this.trimfInput.sendKeys(trimf);
  }

  async getTrimfInput(): Promise<string> {
    return await this.trimfInput.getAttribute('value');
  }

  async setStatutInput(statut: string): Promise<void> {
    await this.statutInput.sendKeys(statut);
  }

  async getStatutInput(): Promise<string> {
    return await this.statutInput.getAttribute('value');
  }

  async setDateEmbaucheInput(dateEmbauche: string): Promise<void> {
    await this.dateEmbaucheInput.sendKeys(dateEmbauche);
  }

  async getDateEmbaucheInput(): Promise<string> {
    return await this.dateEmbaucheInput.getAttribute('value');
  }

  async setNationaliteInput(nationalite: string): Promise<void> {
    await this.nationaliteInput.sendKeys(nationalite);
  }

  async getNationaliteInput(): Promise<string> {
    return await this.nationaliteInput.getAttribute('value');
  }

  async compteBanquaireSelectLastOption(): Promise<void> {
    await this.compteBanquaireSelect.all(by.tagName('option')).last().click();
  }

  async compteBanquaireSelectOption(option: string): Promise<void> {
    await this.compteBanquaireSelect.sendKeys(option);
  }

  getCompteBanquaireSelect(): ElementFinder {
    return this.compteBanquaireSelect;
  }

  async getCompteBanquaireSelectedOption(): Promise<string> {
    return await this.compteBanquaireSelect.element(by.css('option:checked')).getText();
  }

  async societeSelectLastOption(): Promise<void> {
    await this.societeSelect.all(by.tagName('option')).last().click();
  }

  async societeSelectOption(option: string): Promise<void> {
    await this.societeSelect.sendKeys(option);
  }

  getSocieteSelect(): ElementFinder {
    return this.societeSelect;
  }

  async getSocieteSelectedOption(): Promise<string> {
    return await this.societeSelect.element(by.css('option:checked')).getText();
  }

  async posteSelectLastOption(): Promise<void> {
    await this.posteSelect.all(by.tagName('option')).last().click();
  }

  async posteSelectOption(option: string): Promise<void> {
    await this.posteSelect.sendKeys(option);
  }

  getPosteSelect(): ElementFinder {
    return this.posteSelect;
  }

  async getPosteSelectedOption(): Promise<string> {
    return await this.posteSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class EmployeDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-employe-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-employe'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
