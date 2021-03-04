import { element, by, ElementFinder } from 'protractor';

export class BanqueComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-banque div table .btn-danger'));
  title = element.all(by.css('jhi-banque div h2#page-heading span')).first();
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

export class BanqueUpdatePage {
  pageTitle = element(by.id('jhi-banque-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomInput = element(by.id('field_nom'));
  numeroCompteInput = element(by.id('field_numeroCompte'));
  codeBanqueInput = element(by.id('field_codeBanque'));
  codeGuichetInput = element(by.id('field_codeGuichet'));
  cleRibInput = element(by.id('field_cleRib'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setNomInput(nom: string): Promise<void> {
    await this.nomInput.sendKeys(nom);
  }

  async getNomInput(): Promise<string> {
    return await this.nomInput.getAttribute('value');
  }

  async setNumeroCompteInput(numeroCompte: string): Promise<void> {
    await this.numeroCompteInput.sendKeys(numeroCompte);
  }

  async getNumeroCompteInput(): Promise<string> {
    return await this.numeroCompteInput.getAttribute('value');
  }

  async setCodeBanqueInput(codeBanque: string): Promise<void> {
    await this.codeBanqueInput.sendKeys(codeBanque);
  }

  async getCodeBanqueInput(): Promise<string> {
    return await this.codeBanqueInput.getAttribute('value');
  }

  async setCodeGuichetInput(codeGuichet: string): Promise<void> {
    await this.codeGuichetInput.sendKeys(codeGuichet);
  }

  async getCodeGuichetInput(): Promise<string> {
    return await this.codeGuichetInput.getAttribute('value');
  }

  async setCleRibInput(cleRib: string): Promise<void> {
    await this.cleRibInput.sendKeys(cleRib);
  }

  async getCleRibInput(): Promise<string> {
    return await this.cleRibInput.getAttribute('value');
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

export class BanqueDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-banque-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-banque'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
