import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { SegurancatrabalhoTrainingTypeModule } from './training-type/training-type.module';
import { SegurancatrabalhoPlantModule } from './plant/plant.module';
import { SegurancatrabalhoPlantTypeModule } from './plant-type/plant-type.module';
import { SegurancatrabalhoEmployeeModule } from './employee/employee.module';
import { SegurancatrabalhoJobRoleModule } from './job-role/job-role.module';
import { SegurancatrabalhoTrainingModule } from './training/training.module';
import { SegurancatrabalhoTrainingItemModule } from './training-item/training-item.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        SegurancatrabalhoTrainingTypeModule,
        SegurancatrabalhoPlantModule,
        SegurancatrabalhoPlantTypeModule,
        SegurancatrabalhoEmployeeModule,
        SegurancatrabalhoJobRoleModule,
        SegurancatrabalhoTrainingModule,
        SegurancatrabalhoTrainingItemModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SegurancatrabalhoEntityModule {}
