import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { BanqueComponentsPage, BanqueDeleteDialog, BanqueUpdatePage } from './banque.page-object';

const expect = chai.expect;

describe('Banque e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let banqueComponentsPage: BanqueComponentsPage;
  let banqueUpdatePage: BanqueUpdatePage;
  let banqueDeleteDialog: BanqueDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Banques', async () => {
    await navBarPage.goToEntity('banque');
    banqueComponentsPage = new BanqueComponentsPage();
    await browser.wait(ec.visibilityOf(banqueComponentsPage.title), 5000);
    expect(await banqueComponentsPage.getTitle()).to.eq('Banques');
    await browser.wait(ec.or(ec.visibilityOf(banqueComponentsPage.entities), ec.visibilityOf(banqueComponentsPage.noResult)), 1000);
  });

  it('should load create Banque page', async () => {
    await banqueComponentsPage.clickOnCreateButton();
    banqueUpdatePage = new BanqueUpdatePage();
    expect(await banqueUpdatePage.getPageTitle()).to.eq('Create or edit a Banque');
    await banqueUpdatePage.cancel();
  });

  it('should create and save Banques', async () => {
    const nbButtonsBeforeCreate = await banqueComponentsPage.countDeleteButtons();

    await banqueComponentsPage.clickOnCreateButton();

    await promise.all([
      banqueUpdatePage.setNomInput('nom'),
      banqueUpdatePage.setNumeroCompteInput('numeroCompte'),
      banqueUpdatePage.setCodeBanqueInput('codeBanque'),
      banqueUpdatePage.setCodeGuichetInput('codeGuichet'),
      banqueUpdatePage.setCleRibInput('cleRib'),
    ]);

    expect(await banqueUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await banqueUpdatePage.getNumeroCompteInput()).to.eq('numeroCompte', 'Expected NumeroCompte value to be equals to numeroCompte');
    expect(await banqueUpdatePage.getCodeBanqueInput()).to.eq('codeBanque', 'Expected CodeBanque value to be equals to codeBanque');
    expect(await banqueUpdatePage.getCodeGuichetInput()).to.eq('codeGuichet', 'Expected CodeGuichet value to be equals to codeGuichet');
    expect(await banqueUpdatePage.getCleRibInput()).to.eq('cleRib', 'Expected CleRib value to be equals to cleRib');

    await banqueUpdatePage.save();
    expect(await banqueUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await banqueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Banque', async () => {
    const nbButtonsBeforeDelete = await banqueComponentsPage.countDeleteButtons();
    await banqueComponentsPage.clickOnLastDeleteButton();

    banqueDeleteDialog = new BanqueDeleteDialog();
    expect(await banqueDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Banque?');
    await banqueDeleteDialog.clickOnConfirmButton();

    expect(await banqueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
