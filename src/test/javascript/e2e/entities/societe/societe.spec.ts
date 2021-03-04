import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SocieteComponentsPage, SocieteDeleteDialog, SocieteUpdatePage } from './societe.page-object';

const expect = chai.expect;

describe('Societe e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let societeComponentsPage: SocieteComponentsPage;
  let societeUpdatePage: SocieteUpdatePage;
  let societeDeleteDialog: SocieteDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Societes', async () => {
    await navBarPage.goToEntity('societe');
    societeComponentsPage = new SocieteComponentsPage();
    await browser.wait(ec.visibilityOf(societeComponentsPage.title), 5000);
    expect(await societeComponentsPage.getTitle()).to.eq('Societes');
    await browser.wait(ec.or(ec.visibilityOf(societeComponentsPage.entities), ec.visibilityOf(societeComponentsPage.noResult)), 1000);
  });

  it('should load create Societe page', async () => {
    await societeComponentsPage.clickOnCreateButton();
    societeUpdatePage = new SocieteUpdatePage();
    expect(await societeUpdatePage.getPageTitle()).to.eq('Create or edit a Societe');
    await societeUpdatePage.cancel();
  });

  it('should create and save Societes', async () => {
    const nbButtonsBeforeCreate = await societeComponentsPage.countDeleteButtons();

    await societeComponentsPage.clickOnCreateButton();

    await promise.all([
      societeUpdatePage.setNomInput('nom'),
      societeUpdatePage.setAdresseInput('adresse'),
      societeUpdatePage.setTelephoneInput('telephone'),
      societeUpdatePage.setFixeInput('fixe'),
      societeUpdatePage.setNineaInput('ninea'),
      societeUpdatePage.setLogoUrlInput('logoUrl'),
    ]);

    expect(await societeUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await societeUpdatePage.getAdresseInput()).to.eq('adresse', 'Expected Adresse value to be equals to adresse');
    expect(await societeUpdatePage.getTelephoneInput()).to.eq('telephone', 'Expected Telephone value to be equals to telephone');
    expect(await societeUpdatePage.getFixeInput()).to.eq('fixe', 'Expected Fixe value to be equals to fixe');
    expect(await societeUpdatePage.getNineaInput()).to.eq('ninea', 'Expected Ninea value to be equals to ninea');
    expect(await societeUpdatePage.getLogoUrlInput()).to.eq('logoUrl', 'Expected LogoUrl value to be equals to logoUrl');

    await societeUpdatePage.save();
    expect(await societeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await societeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Societe', async () => {
    const nbButtonsBeforeDelete = await societeComponentsPage.countDeleteButtons();
    await societeComponentsPage.clickOnLastDeleteButton();

    societeDeleteDialog = new SocieteDeleteDialog();
    expect(await societeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Societe?');
    await societeDeleteDialog.clickOnConfirmButton();

    expect(await societeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
