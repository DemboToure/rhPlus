import { element, by, ElementFinder } from 'protractor';

export class AppConfigurationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-app-configuration div table .btn-danger'));
  title = element.all(by.css('jhi-app-configuration div h2#page-heading span')).first();
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

export class AppConfigurationUpdatePage {
  pageTitle = element(by.id('jhi-app-configuration-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomEntrepriseInput = element(by.id('field_nomEntreprise'));
  adresseInput = element(by.id('field_adresse'));
  telephoneInput = element(by.id('field_telephone'));
  fixeInput = element(by.id('field_fixe'));
  nineaInput = element(by.id('field_ninea'));
  logoUrlInput = element(by.id('field_logoUrl'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getText();
  }

  async setNomEntrepriseInput(nomEntreprise: string): Promise<void> {
    await this.nomEntrepriseInput.sendKeys(nomEntreprise);
  }

  async getNomEntrepriseInput(): Promise<string> {
    return await this.nomEntrepriseInput.getAttribute('value');
  }

  async setAdresseInput(adresse: string): Promise<void> {
    await this.adresseInput.sendKeys(adresse);
  }

  async getAdresseInput(): Promise<string> {
    return await this.adresseInput.getAttribute('value');
  }

  async setTelephoneInput(telephone: string): Promise<void> {
    await this.telephoneInput.sendKeys(telephone);
  }

  async getTelephoneInput(): Promise<string> {
    return await this.telephoneInput.getAttribute('value');
  }

  async setFixeInput(fixe: string): Promise<void> {
    await this.fixeInput.sendKeys(fixe);
  }

  async getFixeInput(): Promise<string> {
    return await this.fixeInput.getAttribute('value');
  }

  async setNineaInput(ninea: string): Promise<void> {
    await this.nineaInput.sendKeys(ninea);
  }

  async getNineaInput(): Promise<string> {
    return await this.nineaInput.getAttribute('value');
  }

  async setLogoUrlInput(logoUrl: string): Promise<void> {
    await this.logoUrlInput.sendKeys(logoUrl);
  }

  async getLogoUrlInput(): Promise<string> {
    return await this.logoUrlInput.getAttribute('value');
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

export class AppConfigurationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-appConfiguration-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-appConfiguration'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getText();
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
