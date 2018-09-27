import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJobRole } from 'app/shared/model/job-role.model';

@Component({
    selector: 'jhi-job-role-detail',
    templateUrl: './job-role-detail.component.html'
})
export class JobRoleDetailComponent implements OnInit {
    jobRole: IJobRole;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jobRole }) => {
            this.jobRole = jobRole;
        });
    }

    previousState() {
        window.history.back();
    }
}
