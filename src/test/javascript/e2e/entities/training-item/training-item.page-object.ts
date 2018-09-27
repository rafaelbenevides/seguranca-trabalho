import { element, by, ElementFinder } from 'protractor';

export class TrainingItemComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-training-item div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class TrainingItemUpdatePage {
    pageTitle = element(by.id('jhi-training-item-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    dateInput = element(by.id('field_date'));
    trainingApplicableSelect = element(by.id('field_trainingApplicable'));
    certificateValidityInput = element(by.id('field_certificateValidity'));
    hoursOfTrainingInput = element(by.id('field_hoursOfTraining'));
    trainingSelect = element(by.id('field_training'));
    trainingTypeSelect = element(by.id('field_trainingType'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async setDateInput(date) {
        await this.dateInput.sendKeys(date);
    }

    async getDateInput() {
        return this.dateInput.getAttribute('value');
    }

    async setTrainingApplicableSelect(trainingApplicable) {
        await this.trainingApplicableSelect.sendKeys(trainingApplicable);
    }

    async getTrainingApplicableSelect() {
        return this.trainingApplicableSelect.element(by.css('option:checked')).getText();
    }

    async trainingApplicableSelectLastOption() {
        await this.trainingApplicableSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async setCertificateValidityInput(certificateValidity) {
        await this.certificateValidityInput.sendKeys(certificateValidity);
    }

    async getCertificateValidityInput() {
        return this.certificateValidityInput.getAttribute('value');
    }

    async setHoursOfTrainingInput(hoursOfTraining) {
        await this.hoursOfTrainingInput.sendKeys(hoursOfTraining);
    }

    async getHoursOfTrainingInput() {
        return this.hoursOfTrainingInput.getAttribute('value');
    }

    async trainingSelectLastOption() {
        await this.trainingSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async trainingSelectOption(option) {
        await this.trainingSelect.sendKeys(option);
    }

    getTrainingSelect(): ElementFinder {
        return this.trainingSelect;
    }

    async getTrainingSelectedOption() {
        return this.trainingSelect.element(by.css('option:checked')).getText();
    }

    async trainingTypeSelectLastOption() {
        await this.trainingTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async trainingTypeSelectOption(option) {
        await this.trainingTypeSelect.sendKeys(option);
    }

    getTrainingTypeSelect(): ElementFinder {
        return this.trainingTypeSelect;
    }

    async getTrainingTypeSelectedOption() {
        return this.trainingTypeSelect.element(by.css('option:checked')).getText();
    }

    async save() {
        await this.saveButton.click();
    }

    async cancel() {
        await this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
