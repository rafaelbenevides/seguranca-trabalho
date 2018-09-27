import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TrainingTypeComponentsPage, TrainingTypeUpdatePage } from './training-type.page-object';

describe('TrainingType e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let trainingTypeUpdatePage: TrainingTypeUpdatePage;
    let trainingTypeComponentsPage: TrainingTypeComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load TrainingTypes', async () => {
        await navBarPage.goToEntity('training-type');
        trainingTypeComponentsPage = new TrainingTypeComponentsPage();
        expect(await trainingTypeComponentsPage.getTitle()).toMatch(/segurancatrabalhoApp.trainingType.home.title/);
    });

    it('should load create TrainingType page', async () => {
        await trainingTypeComponentsPage.clickOnCreateButton();
        trainingTypeUpdatePage = new TrainingTypeUpdatePage();
        expect(await trainingTypeUpdatePage.getPageTitle()).toMatch(/segurancatrabalhoApp.trainingType.home.createOrEditLabel/);
        await trainingTypeUpdatePage.cancel();
    });

    it('should create and save TrainingTypes', async () => {
        await trainingTypeComponentsPage.clickOnCreateButton();
        await trainingTypeUpdatePage.setNameInput('name');
        expect(await trainingTypeUpdatePage.getNameInput()).toMatch('name');
        await trainingTypeUpdatePage.save();
        expect(await trainingTypeUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
