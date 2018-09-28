import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ITraining, Training } from 'app/shared/model/training.model';
import { TrainingService } from './training.service';
import { TrainingComponent } from './training.component';
import { TrainingDetailComponent } from './training-detail.component';
import { TrainingUpdateComponent } from './training-update.component';
import { TrainingDeletePopupComponent } from './training-delete-dialog.component';
import { ITrainingItem, TrainingItem } from 'app/shared/model/training-item.model';
import { TrainingTypeService } from 'app/entities/training-type';
import { ITrainingType } from 'app/shared/model/training-type.model';

@Injectable({ providedIn: 'root' })
export class TrainingResolve implements Resolve<ITraining> {
    constructor(private service: TrainingService, private trainingTypeService: TrainingTypeService) {}

    private newTrainingItems(): ITrainingItem[] {
        const items: ITrainingItem[] = [];
        this.trainingTypeService.query().subscribe(
            (res: HttpResponse<ITrainingType[]>) => {
                res.body.forEach(trainingType => {
                    const item: ITrainingItem = new TrainingItem();
                    item.trainingTypeId = trainingType.id;
                    item.trainingType = trainingType;
                    items.push(item);
                });
            },
            (res: HttpErrorResponse) => console.log('Not found training type, message: ', res.message)
        );
        return items;
    }

    private newTraining(): ITraining {
        const training: ITraining = new Training();
        training.items = this.newTrainingItems();
        return training;
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((training: HttpResponse<ITraining>) => training.body));
        }
        return of(this.newTraining());
    }
}

export const trainingRoute: Routes = [
    {
        path: 'training',
        component: TrainingComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.training.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'training/:id/view',
        component: TrainingDetailComponent,
        resolve: {
            training: TrainingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.training.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'training/new',
        component: TrainingUpdateComponent,
        resolve: {
            training: TrainingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.training.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'training/:id/edit',
        component: TrainingUpdateComponent,
        resolve: {
            training: TrainingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.training.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const trainingPopupRoute: Routes = [
    {
        path: 'training/:id/delete',
        component: TrainingDeletePopupComponent,
        resolve: {
            training: TrainingResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.training.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
