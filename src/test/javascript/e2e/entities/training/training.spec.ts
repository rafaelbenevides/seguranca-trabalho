import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TrainingComponentsPage, TrainingUpdatePage } from './training.page-object';

describe('Training e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let trainingUpdatePage: TrainingUpdatePage;
    let trainingComponentsPage: TrainingComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load Trainings', async () => {
        await navBarPage.goToEntity('training');
        trainingComponentsPage = new TrainingComponentsPage();
        expect(await trainingComponentsPage.getTitle()).toMatch(/segurancatrabalhoApp.training.home.title/);
    });

    it('should load create Training page', async () => {
        await trainingComponentsPage.clickOnCreateButton();
        trainingUpdatePage = new TrainingUpdatePage();
        expect(await trainingUpdatePage.getPageTitle()).toMatch(/segurancatrabalhoApp.training.home.createOrEditLabel/);
        await trainingUpdatePage.cancel();
    });

    it('should create and save Trainings', async () => {
        await trainingComponentsPage.clickOnCreateButton();
        await trainingUpdatePage.plantSelectLastOption();
        await trainingUpdatePage.plantTypeSelectLastOption();
        await trainingUpdatePage.employeeSelectLastOption();
        await trainingUpdatePage.save();
        expect(await trainingUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
