import { element, by, ElementFinder } from 'protractor';

export class ContratComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-contrat div table .btn-danger'));
  title = element.all(by.css('jhi-contrat div h2#page-heading span')).first();
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

export class ContratUpdatePage {
  pageTitle = element(by.id('jhi-contrat-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  dateDebutInput = element(by.id('field_dateDebut'));
  dateFinInput = element(by.id('field_dateFin'));
  salaireInput = element(by.id('field_salaire'));
  documentInput = element(by.id('field_document'));

  employeSelect = element(by.id('field_employe'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setDateDebutInput(dateDebut: string): Promise<void> {
    await this.dateDebutInput.sendKeys(dateDebut);
  }

  async getDateDebutInput(): Promise<string> {
    return await this.dateDebutInput.getAttribute('value');
  }

  async setDateFinInput(dateFin: string): Promise<void> {
    await this.dateFinInput.sendKeys(dateFin);
  }

  async getDateFinInput(): Promise<string> {
    return await this.dateFinInput.getAttribute('value');
  }

  async setSalaireInput(salaire: string): Promise<void> {
    await this.salaireInput.sendKeys(salaire);
  }

  async getSalaireInput(): Promise<string> {
    return await this.salaireInput.getAttribute('value');
  }

  async setDocumentInput(document: string): Promise<void> {
    await this.documentInput.sendKeys(document);
  }

  async getDocumentInput(): Promise<string> {
    return await this.documentInput.getAttribute('value');
  }

  async employeSelectLastOption(): Promise<void> {
    await this.employeSelect.all(by.tagName('option')).last().click();
  }

  async employeSelectOption(option: string): Promise<void> {
    await this.employeSelect.sendKeys(option);
  }

  getEmployeSelect(): ElementFinder {
    return this.employeSelect;
  }

  async getEmployeSelectedOption(): Promise<string> {
    return await this.employeSelect.element(by.css('option:checked')).getText();
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

export class ContratDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-contrat-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-contrat'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
