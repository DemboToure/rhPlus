import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EnfantComponentsPage, EnfantDeleteDialog, EnfantUpdatePage } from './enfant.page-object';

const expect = chai.expect;

describe('Enfant e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let enfantComponentsPage: EnfantComponentsPage;
  let enfantUpdatePage: EnfantUpdatePage;
  let enfantDeleteDialog: EnfantDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Enfants', async () => {
    await navBarPage.goToEntity('enfant');
    enfantComponentsPage = new EnfantComponentsPage();
    await browser.wait(ec.visibilityOf(enfantComponentsPage.title), 5000);
    expect(await enfantComponentsPage.getTitle()).to.eq('Enfants');
    await browser.wait(ec.or(ec.visibilityOf(enfantComponentsPage.entities), ec.visibilityOf(enfantComponentsPage.noResult)), 1000);
  });

  it('should load create Enfant page', async () => {
    await enfantComponentsPage.clickOnCreateButton();
    enfantUpdatePage = new EnfantUpdatePage();
    expect(await enfantUpdatePage.getPageTitle()).to.eq('Create or edit a Enfant');
    await enfantUpdatePage.cancel();
  });

  it('should create and save Enfants', async () => {
    const nbButtonsBeforeCreate = await enfantComponentsPage.countDeleteButtons();

    await enfantComponentsPage.clickOnCreateButton();

    await promise.all([
      enfantUpdatePage.setPrenomInput('prenom'),
      enfantUpdatePage.setNomInput('nom'),
      enfantUpdatePage.setDateNaissanceInput('2000-12-31'),
      enfantUpdatePage.setLieuNaissanceInput('lieuNaissance'),
      enfantUpdatePage.employeSelectLastOption(),
    ]);

    expect(await enfantUpdatePage.getPrenomInput()).to.eq('prenom', 'Expected Prenom value to be equals to prenom');
    expect(await enfantUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await enfantUpdatePage.getDateNaissanceInput()).to.eq('2000-12-31', 'Expected dateNaissance value to be equals to 2000-12-31');
    expect(await enfantUpdatePage.getLieuNaissanceInput()).to.eq(
      'lieuNaissance',
      'Expected LieuNaissance value to be equals to lieuNaissance'
    );

    await enfantUpdatePage.save();
    expect(await enfantUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await enfantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Enfant', async () => {
    const nbButtonsBeforeDelete = await enfantComponentsPage.countDeleteButtons();
    await enfantComponentsPage.clickOnLastDeleteButton();

    enfantDeleteDialog = new EnfantDeleteDialog();
    expect(await enfantDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Enfant?');
    await enfantDeleteDialog.clickOnConfirmButton();

    expect(await enfantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
