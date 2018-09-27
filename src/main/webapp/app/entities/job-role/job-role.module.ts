import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SegurancatrabalhoSharedModule } from 'app/shared';
import {
    JobRoleComponent,
    JobRoleDetailComponent,
    JobRoleUpdateComponent,
    JobRoleDeletePopupComponent,
    JobRoleDeleteDialogComponent,
    jobRoleRoute,
    jobRolePopupRoute
} from './';

const ENTITY_STATES = [...jobRoleRoute, ...jobRolePopupRoute];

@NgModule({
    imports: [SegurancatrabalhoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JobRoleComponent,
        JobRoleDetailComponent,
        JobRoleUpdateComponent,
        JobRoleDeleteDialogComponent,
        JobRoleDeletePopupComponent
    ],
    entryComponents: [JobRoleComponent, JobRoleUpdateComponent, JobRoleDeleteDialogComponent, JobRoleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SegurancatrabalhoJobRoleModule {}
