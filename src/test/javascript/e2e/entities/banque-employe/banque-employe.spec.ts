import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { BanqueEmployeComponentsPage, BanqueEmployeDeleteDialog, BanqueEmployeUpdatePage } from './banque-employe.page-object';

const expect = chai.expect;

describe('BanqueEmploye e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let banqueEmployeComponentsPage: BanqueEmployeComponentsPage;
  let banqueEmployeUpdatePage: BanqueEmployeUpdatePage;
  let banqueEmployeDeleteDialog: BanqueEmployeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load BanqueEmployes', async () => {
    await navBarPage.goToEntity('banque-employe');
    banqueEmployeComponentsPage = new BanqueEmployeComponentsPage();
    await browser.wait(ec.visibilityOf(banqueEmployeComponentsPage.title), 5000);
    expect(await banqueEmployeComponentsPage.getTitle()).to.eq('Banque Employes');
    await browser.wait(
      ec.or(ec.visibilityOf(banqueEmployeComponentsPage.entities), ec.visibilityOf(banqueEmployeComponentsPage.noResult)),
      1000
    );
  });

  it('should load create BanqueEmploye page', async () => {
    await banqueEmployeComponentsPage.clickOnCreateButton();
    banqueEmployeUpdatePage = new BanqueEmployeUpdatePage();
    expect(await banqueEmployeUpdatePage.getPageTitle()).to.eq('Create or edit a Banque Employe');
    await banqueEmployeUpdatePage.cancel();
  });

  it('should create and save BanqueEmployes', async () => {
    const nbButtonsBeforeCreate = await banqueEmployeComponentsPage.countDeleteButtons();

    await banqueEmployeComponentsPage.clickOnCreateButton();

    await promise.all([
      banqueEmployeUpdatePage.setNomInput('nom'),
      banqueEmployeUpdatePage.setNumeroCompteInput('numeroCompte'),
      banqueEmployeUpdatePage.setCodeBanqueInput('codeBanque'),
      banqueEmployeUpdatePage.setCodeGuichetInput('codeGuichet'),
      banqueEmployeUpdatePage.setCleRibInput('cleRib'),
      banqueEmployeUpdatePage.setPdfDomiciliationInput('pdfDomiciliation'),
      banqueEmployeUpdatePage.banqueSelectLastOption(),
    ]);

    expect(await banqueEmployeUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await banqueEmployeUpdatePage.getNumeroCompteInput()).to.eq(
      'numeroCompte',
      'Expected NumeroCompte value to be equals to numeroCompte'
    );
    expect(await banqueEmployeUpdatePage.getCodeBanqueInput()).to.eq('codeBanque', 'Expected CodeBanque value to be equals to codeBanque');
    expect(await banqueEmployeUpdatePage.getCodeGuichetInput()).to.eq(
      'codeGuichet',
      'Expected CodeGuichet value to be equals to codeGuichet'
    );
    expect(await banqueEmployeUpdatePage.getCleRibInput()).to.eq('cleRib', 'Expected CleRib value to be equals to cleRib');
    expect(await banqueEmployeUpdatePage.getPdfDomiciliationInput()).to.eq(
      'pdfDomiciliation',
      'Expected PdfDomiciliation value to be equals to pdfDomiciliation'
    );

    await banqueEmployeUpdatePage.save();
    expect(await banqueEmployeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await banqueEmployeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last BanqueEmploye', async () => {
    const nbButtonsBeforeDelete = await banqueEmployeComponentsPage.countDeleteButtons();
    await banqueEmployeComponentsPage.clickOnLastDeleteButton();

    banqueEmployeDeleteDialog = new BanqueEmployeDeleteDialog();
    expect(await banqueEmployeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Banque Employe?');
    await banqueEmployeDeleteDialog.clickOnConfirmButton();

    expect(await banqueEmployeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
