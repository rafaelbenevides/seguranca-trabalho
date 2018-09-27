import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SegurancatrabalhoSharedModule } from 'app/shared';
import {
    PlantTypeComponent,
    PlantTypeDetailComponent,
    PlantTypeUpdateComponent,
    PlantTypeDeletePopupComponent,
    PlantTypeDeleteDialogComponent,
    plantTypeRoute,
    plantTypePopupRoute
} from './';

const ENTITY_STATES = [...plantTypeRoute, ...plantTypePopupRoute];

@NgModule({
    imports: [SegurancatrabalhoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlantTypeComponent,
        PlantTypeDetailComponent,
        PlantTypeUpdateComponent,
        PlantTypeDeleteDialogComponent,
        PlantTypeDeletePopupComponent
    ],
    entryComponents: [PlantTypeComponent, PlantTypeUpdateComponent, PlantTypeDeleteDialogComponent, PlantTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SegurancatrabalhoPlantTypeModule {}
