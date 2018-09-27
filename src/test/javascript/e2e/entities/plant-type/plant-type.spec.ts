import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PlantTypeComponentsPage, PlantTypeUpdatePage } from './plant-type.page-object';

describe('PlantType e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let plantTypeUpdatePage: PlantTypeUpdatePage;
    let plantTypeComponentsPage: PlantTypeComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load PlantTypes', async () => {
        await navBarPage.goToEntity('plant-type');
        plantTypeComponentsPage = new PlantTypeComponentsPage();
        expect(await plantTypeComponentsPage.getTitle()).toMatch(/segurancatrabalhoApp.plantType.home.title/);
    });

    it('should load create PlantType page', async () => {
        await plantTypeComponentsPage.clickOnCreateButton();
        plantTypeUpdatePage = new PlantTypeUpdatePage();
        expect(await plantTypeUpdatePage.getPageTitle()).toMatch(/segurancatrabalhoApp.plantType.home.createOrEditLabel/);
        await plantTypeUpdatePage.cancel();
    });

    it('should create and save PlantTypes', async () => {
        await plantTypeComponentsPage.clickOnCreateButton();
        await plantTypeUpdatePage.setNameInput('name');
        expect(await plantTypeUpdatePage.getNameInput()).toMatch('name');
        await plantTypeUpdatePage.save();
        expect(await plantTypeUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
