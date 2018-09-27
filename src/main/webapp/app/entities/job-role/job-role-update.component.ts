import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IJobRole } from 'app/shared/model/job-role.model';
import { JobRoleService } from './job-role.service';

@Component({
    selector: 'jhi-job-role-update',
    templateUrl: './job-role-update.component.html'
})
export class JobRoleUpdateComponent implements OnInit {
    private _jobRole: IJobRole;
    isSaving: boolean;

    constructor(private jobRoleService: JobRoleService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jobRole }) => {
            this.jobRole = jobRole;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.jobRole.id !== undefined) {
            this.subscribeToSaveResponse(this.jobRoleService.update(this.jobRole));
        } else {
            this.subscribeToSaveResponse(this.jobRoleService.create(this.jobRole));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IJobRole>>) {
        result.subscribe((res: HttpResponse<IJobRole>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get jobRole() {
        return this._jobRole;
    }

    set jobRole(jobRole: IJobRole) {
        this._jobRole = jobRole;
    }
}
