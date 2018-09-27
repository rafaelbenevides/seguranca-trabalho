import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IJobRole } from 'app/shared/model/job-role.model';
import { Principal } from 'app/core';
import { JobRoleService } from './job-role.service';

@Component({
    selector: 'jhi-job-role',
    templateUrl: './job-role.component.html'
})
export class JobRoleComponent implements OnInit, OnDestroy {
    jobRoles: IJobRole[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private jobRoleService: JobRoleService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.jobRoleService.query().subscribe(
            (res: HttpResponse<IJobRole[]>) => {
                this.jobRoles = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInJobRoles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IJobRole) {
        return item.id;
    }

    registerChangeInJobRoles() {
        this.eventSubscriber = this.eventManager.subscribe('jobRoleListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
