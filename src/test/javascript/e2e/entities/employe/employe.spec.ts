import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EmployeComponentsPage, EmployeDeleteDialog, EmployeUpdatePage } from './employe.page-object';

const expect = chai.expect;

describe('Employe e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let employeComponentsPage: EmployeComponentsPage;
  let employeUpdatePage: EmployeUpdatePage;
  let employeDeleteDialog: EmployeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Employes', async () => {
    await navBarPage.goToEntity('employe');
    employeComponentsPage = new EmployeComponentsPage();
    await browser.wait(ec.visibilityOf(employeComponentsPage.title), 5000);
    expect(await employeComponentsPage.getTitle()).to.eq('Employes');
    await browser.wait(ec.or(ec.visibilityOf(employeComponentsPage.entities), ec.visibilityOf(employeComponentsPage.noResult)), 1000);
  });

  it('should load create Employe page', async () => {
    await employeComponentsPage.clickOnCreateButton();
    employeUpdatePage = new EmployeUpdatePage();
    expect(await employeUpdatePage.getPageTitle()).to.eq('Create or edit a Employe');
    await employeUpdatePage.cancel();
  });

  it('should create and save Employes', async () => {
    const nbButtonsBeforeCreate = await employeComponentsPage.countDeleteButtons();

    await employeComponentsPage.clickOnCreateButton();

    await promise.all([
      employeUpdatePage.setPrenomInput('prenom'),
      employeUpdatePage.setNomInput('nom'),
      employeUpdatePage.setDateNaissanceInput('2000-12-31'),
      employeUpdatePage.setLieuNaissanceInput('lieuNaissance'),
      employeUpdatePage.setCniInput('cni'),
      employeUpdatePage.setProfessionInput('profession'),
      employeUpdatePage.setNumeroCaisseSecuriteInput('numeroCaisseSecurite'),
      employeUpdatePage.setNumeroIpresInput('numeroIpres'),
      employeUpdatePage.setMatriculeInput('matricule'),
      employeUpdatePage.setImageUrlInput('imageUrl'),
      employeUpdatePage.setPrenomPereInput('prenomPere'),
      employeUpdatePage.setPrenomMereInput('prenomMere'),
      employeUpdatePage.setNomMereInput('nomMere'),
      employeUpdatePage.situationMatrimonialeSelectLastOption(),
      employeUpdatePage.sexeSelectLastOption(),
      employeUpdatePage.setTrimfInput('trimf'),
      employeUpdatePage.setStatutInput('statut'),
      employeUpdatePage.setDateEmbaucheInput('2000-12-31'),
      employeUpdatePage.setNationaliteInput('nationalite'),
      employeUpdatePage.compteBanquaireSelectLastOption(),
      employeUpdatePage.societeSelectLastOption(),
      employeUpdatePage.posteSelectLastOption(),
    ]);

    expect(await employeUpdatePage.getPrenomInput()).to.eq('prenom', 'Expected Prenom value to be equals to prenom');
    expect(await employeUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await employeUpdatePage.getDateNaissanceInput()).to.eq('2000-12-31', 'Expected dateNaissance value to be equals to 2000-12-31');
    expect(await employeUpdatePage.getLieuNaissanceInput()).to.eq(
      'lieuNaissance',
      'Expected LieuNaissance value to be equals to lieuNaissance'
    );
    expect(await employeUpdatePage.getCniInput()).to.eq('cni', 'Expected Cni value to be equals to cni');
    expect(await employeUpdatePage.getProfessionInput()).to.eq('profession', 'Expected Profession value to be equals to profession');
    expect(await employeUpdatePage.getNumeroCaisseSecuriteInput()).to.eq(
      'numeroCaisseSecurite',
      'Expected NumeroCaisseSecurite value to be equals to numeroCaisseSecurite'
    );
    expect(await employeUpdatePage.getNumeroIpresInput()).to.eq('numeroIpres', 'Expected NumeroIpres value to be equals to numeroIpres');
    expect(await employeUpdatePage.getMatriculeInput()).to.eq('matricule', 'Expected Matricule value to be equals to matricule');
    expect(await employeUpdatePage.getImageUrlInput()).to.eq('imageUrl', 'Expected ImageUrl value to be equals to imageUrl');
    expect(await employeUpdatePage.getPrenomPereInput()).to.eq('prenomPere', 'Expected PrenomPere value to be equals to prenomPere');
    expect(await employeUpdatePage.getPrenomMereInput()).to.eq('prenomMere', 'Expected PrenomMere value to be equals to prenomMere');
    expect(await employeUpdatePage.getNomMereInput()).to.eq('nomMere', 'Expected NomMere value to be equals to nomMere');
    expect(await employeUpdatePage.getTrimfInput()).to.eq('trimf', 'Expected Trimf value to be equals to trimf');
    expect(await employeUpdatePage.getStatutInput()).to.eq('statut', 'Expected Statut value to be equals to statut');
    expect(await employeUpdatePage.getDateEmbaucheInput()).to.eq('2000-12-31', 'Expected dateEmbauche value to be equals to 2000-12-31');
    expect(await employeUpdatePage.getNationaliteInput()).to.eq('nationalite', 'Expected Nationalite value to be equals to nationalite');

    await employeUpdatePage.save();
    expect(await employeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await employeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Employe', async () => {
    const nbButtonsBeforeDelete = await employeComponentsPage.countDeleteButtons();
    await employeComponentsPage.clickOnLastDeleteButton();

    employeDeleteDialog = new EmployeDeleteDialog();
    expect(await employeDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Employe?');
    await employeDeleteDialog.clickOnConfirmButton();

    expect(await employeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
