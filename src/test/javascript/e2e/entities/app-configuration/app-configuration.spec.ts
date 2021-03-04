import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AppConfigurationComponentsPage, AppConfigurationDeleteDialog, AppConfigurationUpdatePage } from './app-configuration.page-object';

const expect = chai.expect;

describe('AppConfiguration e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let appConfigurationComponentsPage: AppConfigurationComponentsPage;
  let appConfigurationUpdatePage: AppConfigurationUpdatePage;
  let appConfigurationDeleteDialog: AppConfigurationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load AppConfigurations', async () => {
    await navBarPage.goToEntity('app-configuration');
    appConfigurationComponentsPage = new AppConfigurationComponentsPage();
    await browser.wait(ec.visibilityOf(appConfigurationComponentsPage.title), 5000);
    expect(await appConfigurationComponentsPage.getTitle()).to.eq('App Configurations');
    await browser.wait(
      ec.or(ec.visibilityOf(appConfigurationComponentsPage.entities), ec.visibilityOf(appConfigurationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create AppConfiguration page', async () => {
    await appConfigurationComponentsPage.clickOnCreateButton();
    appConfigurationUpdatePage = new AppConfigurationUpdatePage();
    expect(await appConfigurationUpdatePage.getPageTitle()).to.eq('Create or edit a App Configuration');
    await appConfigurationUpdatePage.cancel();
  });

  it('should create and save AppConfigurations', async () => {
    const nbButtonsBeforeCreate = await appConfigurationComponentsPage.countDeleteButtons();

    await appConfigurationComponentsPage.clickOnCreateButton();

    await promise.all([
      appConfigurationUpdatePage.setNomEntrepriseInput('nomEntreprise'),
      appConfigurationUpdatePage.setAdresseInput('adresse'),
      appConfigurationUpdatePage.setTelephoneInput('telephone'),
      appConfigurationUpdatePage.setFixeInput('fixe'),
      appConfigurationUpdatePage.setNineaInput('ninea'),
      appConfigurationUpdatePage.setLogoUrlInput('logoUrl'),
    ]);

    expect(await appConfigurationUpdatePage.getNomEntrepriseInput()).to.eq(
      'nomEntreprise',
      'Expected NomEntreprise value to be equals to nomEntreprise'
    );
    expect(await appConfigurationUpdatePage.getAdresseInput()).to.eq('adresse', 'Expected Adresse value to be equals to adresse');
    expect(await appConfigurationUpdatePage.getTelephoneInput()).to.eq('telephone', 'Expected Telephone value to be equals to telephone');
    expect(await appConfigurationUpdatePage.getFixeInput()).to.eq('fixe', 'Expected Fixe value to be equals to fixe');
    expect(await appConfigurationUpdatePage.getNineaInput()).to.eq('ninea', 'Expected Ninea value to be equals to ninea');
    expect(await appConfigurationUpdatePage.getLogoUrlInput()).to.eq('logoUrl', 'Expected LogoUrl value to be equals to logoUrl');

    await appConfigurationUpdatePage.save();
    expect(await appConfigurationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await appConfigurationComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last AppConfiguration', async () => {
    const nbButtonsBeforeDelete = await appConfigurationComponentsPage.countDeleteButtons();
    await appConfigurationComponentsPage.clickOnLastDeleteButton();

    appConfigurationDeleteDialog = new AppConfigurationDeleteDialog();
    expect(await appConfigurationDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this App Configuration?');
    await appConfigurationDeleteDialog.clickOnConfirmButton();

    expect(await appConfigurationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
