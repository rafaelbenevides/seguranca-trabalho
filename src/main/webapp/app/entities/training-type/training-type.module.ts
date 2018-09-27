import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SegurancatrabalhoSharedModule } from 'app/shared';
import {
    TrainingTypeComponent,
    TrainingTypeDetailComponent,
    TrainingTypeUpdateComponent,
    TrainingTypeDeletePopupComponent,
    TrainingTypeDeleteDialogComponent,
    trainingTypeRoute,
    trainingTypePopupRoute
} from './';

const ENTITY_STATES = [...trainingTypeRoute, ...trainingTypePopupRoute];

@NgModule({
    imports: [SegurancatrabalhoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TrainingTypeComponent,
        TrainingTypeDetailComponent,
        TrainingTypeUpdateComponent,
        TrainingTypeDeleteDialogComponent,
        TrainingTypeDeletePopupComponent
    ],
    entryComponents: [
        TrainingTypeComponent,
        TrainingTypeUpdateComponent,
        TrainingTypeDeleteDialogComponent,
        TrainingTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SegurancatrabalhoTrainingTypeModule {}
