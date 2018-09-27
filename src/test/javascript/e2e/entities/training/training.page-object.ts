import { element, by, ElementFinder } from 'protractor';

export class TrainingComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-training div h2#page-heading span')).first();

    async clickOnCreateButton() {
        await this.createButton.click();
    }

    async getTitle() {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class TrainingUpdatePage {
    pageTitle = element(by.id('jhi-training-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    plantSelect = element(by.id('field_plant'));
    plantTypeSelect = element(by.id('field_plantType'));
    employeeSelect = element(by.id('field_employee'));

    async getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    async plantSelectLastOption() {
        await this.plantSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async plantSelectOption(option) {
        await this.plantSelect.sendKeys(option);
    }

    getPlantSelect(): ElementFinder {
        return this.plantSelect;
    }

    async getPlantSelectedOption() {
        return this.plantSelect.element(by.css('option:checked')).getText();
    }

    async plantTypeSelectLastOption() {
        await this.plantTypeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async plantTypeSelectOption(option) {
        await this.plantTypeSelect.sendKeys(option);
    }

    getPlantTypeSelect(): ElementFinder {
        return this.plantTypeSelect;
    }

    async getPlantTypeSelectedOption() {
        return this.plantTypeSelect.element(by.css('option:checked')).getText();
    }

    async employeeSelectLastOption() {
        await this.employeeSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    async employeeSelectOption(option) {
        await this.employeeSelect.sendKeys(option);
    }

    getEmployeeSelect(): ElementFinder {
        return this.employeeSelect;
    }

    async getEmployeeSelectedOption() {
        return this.employeeSelect.element(by.css('option:checked')).getText();
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
