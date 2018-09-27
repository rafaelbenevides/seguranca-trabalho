import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TrainingItemComponentsPage, TrainingItemUpdatePage } from './training-item.page-object';

describe('TrainingItem e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let trainingItemUpdatePage: TrainingItemUpdatePage;
    let trainingItemComponentsPage: TrainingItemComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load TrainingItems', async () => {
        await navBarPage.goToEntity('training-item');
        trainingItemComponentsPage = new TrainingItemComponentsPage();
        expect(await trainingItemComponentsPage.getTitle()).toMatch(/segurancatrabalhoApp.trainingItem.home.title/);
    });

    it('should load create TrainingItem page', async () => {
        await trainingItemComponentsPage.clickOnCreateButton();
        trainingItemUpdatePage = new TrainingItemUpdatePage();
        expect(await trainingItemUpdatePage.getPageTitle()).toMatch(/segurancatrabalhoApp.trainingItem.home.createOrEditLabel/);
        await trainingItemUpdatePage.cancel();
    });

    it('should create and save TrainingItems', async () => {
        await trainingItemComponentsPage.clickOnCreateButton();
        await trainingItemUpdatePage.setDateInput('2000-12-31');
        expect(await trainingItemUpdatePage.getDateInput()).toMatch('2000-12-31');
        await trainingItemUpdatePage.trainingApplicableSelectLastOption();
        await trainingItemUpdatePage.setCertificateValidityInput('5');
        expect(await trainingItemUpdatePage.getCertificateValidityInput()).toMatch('5');
        await trainingItemUpdatePage.setHoursOfTrainingInput('5');
        expect(await trainingItemUpdatePage.getHoursOfTrainingInput()).toMatch('5');
        await trainingItemUpdatePage.trainingSelectLastOption();
        await trainingItemUpdatePage.trainingTypeSelectLastOption();
        await trainingItemUpdatePage.save();
        expect(await trainingItemUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
