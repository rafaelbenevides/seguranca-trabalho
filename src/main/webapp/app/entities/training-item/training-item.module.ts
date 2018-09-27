import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SegurancatrabalhoSharedModule } from 'app/shared';
import {
    TrainingItemComponent,
    TrainingItemDetailComponent,
    TrainingItemUpdateComponent,
    TrainingItemDeletePopupComponent,
    TrainingItemDeleteDialogComponent,
    trainingItemRoute,
    trainingItemPopupRoute
} from './';

const ENTITY_STATES = [...trainingItemRoute, ...trainingItemPopupRoute];

@NgModule({
    imports: [SegurancatrabalhoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TrainingItemComponent,
        TrainingItemDetailComponent,
        TrainingItemUpdateComponent,
        TrainingItemDeleteDialogComponent,
        TrainingItemDeletePopupComponent
    ],
    entryComponents: [
        TrainingItemComponent,
        TrainingItemUpdateComponent,
        TrainingItemDeleteDialogComponent,
        TrainingItemDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SegurancatrabalhoTrainingItemModule {}
