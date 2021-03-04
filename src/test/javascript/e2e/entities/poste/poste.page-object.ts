import { element, by, ElementFinder } from 'protractor';

export class PosteComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-poste div table .btn-danger'));
  title = element.all(by.css('jhi-poste div h2#page-heading span')).first();
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

export class PosteUpdatePage {
  pageTitle = element(by.id('jhi-poste-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomInput = element(by.id('field_nom'));
  nombreRequisInput = element(by.id('field_nombreRequis'));

  serviceEntrepriseSelect = element(by.id('field_serviceEntreprise'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setNomInput(nom: string): Promise<void> {
    await this.nomInput.sendKeys(nom);
  }

  async getNomInput(): Promise<string> {
    return await this.nomInput.getAttribute('value');
  }

  async setNombreRequisInput(nombreRequis: string): Promise<void> {
    await this.nombreRequisInput.sendKeys(nombreRequis);
  }

  async getNombreRequisInput(): Promise<string> {
    return await this.nombreRequisInput.getAttribute('value');
  }

  async serviceEntrepriseSelectLastOption(): Promise<void> {
    await this.serviceEntrepriseSelect.all(by.tagName('option')).last().click();
  }

  async serviceEntrepriseSelectOption(option: string): Promise<void> {
    await this.serviceEntrepriseSelect.sendKeys(option);
  }

  getServiceEntrepriseSelect(): ElementFinder {
    return this.serviceEntrepriseSelect;
  }

  async getServiceEntrepriseSelectedOption(): Promise<string> {
    return await this.serviceEntrepriseSelect.element(by.css('option:checked')).getText();
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

export class PosteDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-poste-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-poste'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
