import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EpouxComponentsPage, EpouxDeleteDialog, EpouxUpdatePage } from './epoux.page-object';

const expect = chai.expect;

describe('Epoux e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let epouxComponentsPage: EpouxComponentsPage;
  let epouxUpdatePage: EpouxUpdatePage;
  let epouxDeleteDialog: EpouxDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Epouxes', async () => {
    await navBarPage.goToEntity('epoux');
    epouxComponentsPage = new EpouxComponentsPage();
    await browser.wait(ec.visibilityOf(epouxComponentsPage.title), 5000);
    expect(await epouxComponentsPage.getTitle()).to.eq('Epouxes');
    await browser.wait(ec.or(ec.visibilityOf(epouxComponentsPage.entities), ec.visibilityOf(epouxComponentsPage.noResult)), 1000);
  });

  it('should load create Epoux page', async () => {
    await epouxComponentsPage.clickOnCreateButton();
    epouxUpdatePage = new EpouxUpdatePage();
    expect(await epouxUpdatePage.getPageTitle()).to.eq('Create or edit a Epoux');
    await epouxUpdatePage.cancel();
  });

  it('should create and save Epouxes', async () => {
    const nbButtonsBeforeCreate = await epouxComponentsPage.countDeleteButtons();

    await epouxComponentsPage.clickOnCreateButton();

    await promise.all([
      epouxUpdatePage.setPrenomInput('prenom'),
      epouxUpdatePage.setNomInput('nom'),
      epouxUpdatePage.setDateNaissanceInput('2000-12-31'),
      epouxUpdatePage.setLieuNaissanceInput('lieuNaissance'),
      epouxUpdatePage.employeSelectLastOption(),
    ]);

    expect(await epouxUpdatePage.getPrenomInput()).to.eq('prenom', 'Expected Prenom value to be equals to prenom');
    expect(await epouxUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await epouxUpdatePage.getDateNaissanceInput()).to.eq('2000-12-31', 'Expected dateNaissance value to be equals to 2000-12-31');
    expect(await epouxUpdatePage.getLieuNaissanceInput()).to.eq(
      'lieuNaissance',
      'Expected LieuNaissance value to be equals to lieuNaissance'
    );

    await epouxUpdatePage.save();
    expect(await epouxUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await epouxComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Epoux', async () => {
    const nbButtonsBeforeDelete = await epouxComponentsPage.countDeleteButtons();
    await epouxComponentsPage.clickOnLastDeleteButton();

    epouxDeleteDialog = new EpouxDeleteDialog();
    expect(await epouxDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Epoux?');
    await epouxDeleteDialog.clickOnConfirmButton();

    expect(await epouxComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
