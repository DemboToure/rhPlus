import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ServiceEntrepriseComponentsPage,
  ServiceEntrepriseDeleteDialog,
  ServiceEntrepriseUpdatePage,
} from './service-entreprise.page-object';

const expect = chai.expect;

describe('ServiceEntreprise e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let serviceEntrepriseComponentsPage: ServiceEntrepriseComponentsPage;
  let serviceEntrepriseUpdatePage: ServiceEntrepriseUpdatePage;
  let serviceEntrepriseDeleteDialog: ServiceEntrepriseDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ServiceEntreprises', async () => {
    await navBarPage.goToEntity('service-entreprise');
    serviceEntrepriseComponentsPage = new ServiceEntrepriseComponentsPage();
    await browser.wait(ec.visibilityOf(serviceEntrepriseComponentsPage.title), 5000);
    expect(await serviceEntrepriseComponentsPage.getTitle()).to.eq('Service Entreprises');
    await browser.wait(
      ec.or(ec.visibilityOf(serviceEntrepriseComponentsPage.entities), ec.visibilityOf(serviceEntrepriseComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ServiceEntreprise page', async () => {
    await serviceEntrepriseComponentsPage.clickOnCreateButton();
    serviceEntrepriseUpdatePage = new ServiceEntrepriseUpdatePage();
    expect(await serviceEntrepriseUpdatePage.getPageTitle()).to.eq('Create or edit a Service Entreprise');
    await serviceEntrepriseUpdatePage.cancel();
  });

  it('should create and save ServiceEntreprises', async () => {
    const nbButtonsBeforeCreate = await serviceEntrepriseComponentsPage.countDeleteButtons();

    await serviceEntrepriseComponentsPage.clickOnCreateButton();

    await promise.all([
      serviceEntrepriseUpdatePage.setNomInput('nom'),
      serviceEntrepriseUpdatePage.setAdresseInput('adresse'),
      serviceEntrepriseUpdatePage.setTelInput('tel'),
      serviceEntrepriseUpdatePage.setResponsableInput('5'),
    ]);

    expect(await serviceEntrepriseUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await serviceEntrepriseUpdatePage.getAdresseInput()).to.eq('adresse', 'Expected Adresse value to be equals to adresse');
    expect(await serviceEntrepriseUpdatePage.getTelInput()).to.eq('tel', 'Expected Tel value to be equals to tel');
    expect(await serviceEntrepriseUpdatePage.getResponsableInput()).to.eq('5', 'Expected responsable value to be equals to 5');

    await serviceEntrepriseUpdatePage.save();
    expect(await serviceEntrepriseUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await serviceEntrepriseComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ServiceEntreprise', async () => {
    const nbButtonsBeforeDelete = await serviceEntrepriseComponentsPage.countDeleteButtons();
    await serviceEntrepriseComponentsPage.clickOnLastDeleteButton();

    serviceEntrepriseDeleteDialog = new ServiceEntrepriseDeleteDialog();
    expect(await serviceEntrepriseDeleteDialog.getDialogTitle()).to.eq('Are you sure you want to delete this Service Entreprise?');
    await serviceEntrepriseDeleteDialog.clickOnConfirmButton();

    expect(await serviceEntrepriseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
