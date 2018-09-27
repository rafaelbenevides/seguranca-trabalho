import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TrainingItem } from 'app/shared/model/training-item.model';
import { TrainingItemService } from './training-item.service';
import { TrainingItemComponent } from './training-item.component';
import { TrainingItemDetailComponent } from './training-item-detail.component';
import { TrainingItemUpdateComponent } from './training-item-update.component';
import { TrainingItemDeletePopupComponent } from './training-item-delete-dialog.component';
import { ITrainingItem } from 'app/shared/model/training-item.model';

@Injectable({ providedIn: 'root' })
export class TrainingItemResolve implements Resolve<ITrainingItem> {
    constructor(private service: TrainingItemService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((trainingItem: HttpResponse<TrainingItem>) => trainingItem.body));
        }
        return of(new TrainingItem());
    }
}

export const trainingItemRoute: Routes = [
    {
        path: 'training-item',
        component: TrainingItemComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.trainingItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'training-item/:id/view',
        component: TrainingItemDetailComponent,
        resolve: {
            trainingItem: TrainingItemResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.trainingItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'training-item/new',
        component: TrainingItemUpdateComponent,
        resolve: {
            trainingItem: TrainingItemResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.trainingItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'training-item/:id/edit',
        component: TrainingItemUpdateComponent,
        resolve: {
            trainingItem: TrainingItemResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.trainingItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const trainingItemPopupRoute: Routes = [
    {
        path: 'training-item/:id/delete',
        component: TrainingItemDeletePopupComponent,
        resolve: {
            trainingItem: TrainingItemResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.trainingItem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
