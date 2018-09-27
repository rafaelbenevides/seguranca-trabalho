import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PlantComponentsPage, PlantUpdatePage } from './plant.page-object';

describe('Plant e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let plantUpdatePage: PlantUpdatePage;
    let plantComponentsPage: PlantComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Plants', async () => {
        await navBarPage.goToEntity('plant');
        plantComponentsPage = new PlantComponentsPage();
        expect(await plantComponentsPage.getTitle()).toMatch(/segurancatrabalhoApp.plant.home.title/);
    });

    it('should load create Plant page', async () => {
        await plantComponentsPage.clickOnCreateButton();
        plantUpdatePage = new PlantUpdatePage();
        expect(await plantUpdatePage.getPageTitle()).toMatch(/segurancatrabalhoApp.plant.home.createOrEditLabel/);
        await plantUpdatePage.cancel();
    });

    it('should create and save Plants', async () => {
        await plantComponentsPage.clickOnCreateButton();
        await plantUpdatePage.setNameInput('name');
        expect(await plantUpdatePage.getNameInput()).toMatch('name');
        await plantUpdatePage.save();
        expect(await plantUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
