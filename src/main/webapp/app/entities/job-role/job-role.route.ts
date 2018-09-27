import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { JobRole } from 'app/shared/model/job-role.model';
import { JobRoleService } from './job-role.service';
import { JobRoleComponent } from './job-role.component';
import { JobRoleDetailComponent } from './job-role-detail.component';
import { JobRoleUpdateComponent } from './job-role-update.component';
import { JobRoleDeletePopupComponent } from './job-role-delete-dialog.component';
import { IJobRole } from 'app/shared/model/job-role.model';

@Injectable({ providedIn: 'root' })
export class JobRoleResolve implements Resolve<IJobRole> {
    constructor(private service: JobRoleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((jobRole: HttpResponse<JobRole>) => jobRole.body));
        }
        return of(new JobRole());
    }
}

export const jobRoleRoute: Routes = [
    {
        path: 'job-role',
        component: JobRoleComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.jobRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-role/:id/view',
        component: JobRoleDetailComponent,
        resolve: {
            jobRole: JobRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.jobRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-role/new',
        component: JobRoleUpdateComponent,
        resolve: {
            jobRole: JobRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.jobRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'job-role/:id/edit',
        component: JobRoleUpdateComponent,
        resolve: {
            jobRole: JobRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.jobRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jobRolePopupRoute: Routes = [
    {
        path: 'job-role/:id/delete',
        component: JobRoleDeletePopupComponent,
        resolve: {
            jobRole: JobRoleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.jobRole.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
