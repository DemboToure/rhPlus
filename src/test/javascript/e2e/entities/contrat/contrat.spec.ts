import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ContratComponentsPage, ContratDeleteDialog, ContratUpdatePage } from './contrat.page-object';

const expect = chai.expect;

describe('Contrat e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let contratComponentsPage: ContratComponentsPage;
  let contratUpdatePage: ContratUpdatePage;
  let contratDeleteDialog: ContratDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Contrats', async () => {
    await navBarPage.goToEntity('contrat');
    contratComponentsPage = new ContratComponentsPage();
    await browser.wait(ec.visibilityOf(contratComponentsPage.title), 5000);
    expect(await contratComponentsPage.getTitle()).to.eq('Contrats');
    await browser.wait(ec.or(ec.visibilityOf(contratComponentsPage.entities), ec.visibilityOf(contratComponentsPage.noResult)), 1000);
  });

  it('should load create Contrat page', async () => {
    await contratComponentsPage.clickOnCreateButton();
    contratUpdatePage = new ContratUpdatePage();
    expect(await contratUpdatePage.getPageTitle()).to.eq('Create or edit a Contrat');
    await contratUpdatePage.cancel();
  });

  it('should create and save Contrats', async () => {
    const nbButtonsBeforeCreate = await contratComponentsPage.countDeleteButtons();

    await contratComponentsPage.clickOnCreateButton();

    await promise.all([
      contratUpdatePage.setDateDebutInput('2000-12-31'),
      contratUpdatePage.setDateFinInput('2000-12-31'),
      contratUpdatePage.setSalaireInput('5'),
      contratUpdatePage.setDocumentInput('document'),
      contratUpdatePage.employeSelectLastOption(),
    ]);

    expect(await contratUpdatePage.getDateDebutInput()).to.eq('2000-12-31', 'Expected dateDebut value to be equals to 2000-12-31');
    expect(await contratUpdatePage.getDateFinInput()).to.eq('2000-12-31', 'Expected dateFin value to be equals to 2000-12-31');
    expect(await contratUpdatePage.getSalaireInput()).to.eq('5', 'Expected salaire value to be equals to 5');
    expect(await contratUpdatePage.getDocumentInput()).to.eq('document', 'Expected Document value to be equals to document');

    await contratUpdatePage.save();
    expect(await contratUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await contratComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Contrat', async () => {
    const nbButtonsBeforeDelete = await contratComponentsPage.countDeleteButtons();
    await contratComponentsPage.clickOnLastDeleteButton();

    contratDeleteDialog = new ContratDeleteDialog();
    expect(await contratDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Contrat?');
    await contratDeleteDialog.clickOnConfirmButton();

    expect(await contratComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
