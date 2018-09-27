import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { JobRoleComponentsPage, JobRoleUpdatePage } from './job-role.page-object';

describe('JobRole e2e test', () => {
    let navBarPage: NavBarPage;
    let signInPage: SignInPage;
    let jobRoleUpdatePage: JobRoleUpdatePage;
    let jobRoleComponentsPage: JobRoleComponentsPage;

    beforeAll(async () => {
        await browser.get('/');
        navBarPage = new NavBarPage();
        signInPage = await navBarPage.getSignInPage();
        await signInPage.autoSignInUsing('admin', 'admin');
        await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
    });

    it('should load JobRoles', async () => {
        await navBarPage.goToEntity('job-role');
        jobRoleComponentsPage = new JobRoleComponentsPage();
        expect(await jobRoleComponentsPage.getTitle()).toMatch(/segurancatrabalhoApp.jobRole.home.title/);
    });

    it('should load create JobRole page', async () => {
        await jobRoleComponentsPage.clickOnCreateButton();
        jobRoleUpdatePage = new JobRoleUpdatePage();
        expect(await jobRoleUpdatePage.getPageTitle()).toMatch(/segurancatrabalhoApp.jobRole.home.createOrEditLabel/);
        await jobRoleUpdatePage.cancel();
    });

    it('should create and save JobRoles', async () => {
        await jobRoleComponentsPage.clickOnCreateButton();
        await jobRoleUpdatePage.setNameInput('name');
        expect(await jobRoleUpdatePage.getNameInput()).toMatch('name');
        await jobRoleUpdatePage.save();
        expect(await jobRoleUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(async () => {
        await navBarPage.autoSignOut();
    });
});
