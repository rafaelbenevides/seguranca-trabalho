import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { PlantType } from 'app/shared/model/plant-type.model';
import { PlantTypeService } from './plant-type.service';
import { PlantTypeComponent } from './plant-type.component';
import { PlantTypeDetailComponent } from './plant-type-detail.component';
import { PlantTypeUpdateComponent } from './plant-type-update.component';
import { PlantTypeDeletePopupComponent } from './plant-type-delete-dialog.component';
import { IPlantType } from 'app/shared/model/plant-type.model';

@Injectable({ providedIn: 'root' })
export class PlantTypeResolve implements Resolve<IPlantType> {
    constructor(private service: PlantTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((plantType: HttpResponse<PlantType>) => plantType.body));
        }
        return of(new PlantType());
    }
}

export const plantTypeRoute: Routes = [
    {
        path: 'plant-type',
        component: PlantTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.plantType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plant-type/:id/view',
        component: PlantTypeDetailComponent,
        resolve: {
            plantType: PlantTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.plantType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plant-type/new',
        component: PlantTypeUpdateComponent,
        resolve: {
            plantType: PlantTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.plantType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plant-type/:id/edit',
        component: PlantTypeUpdateComponent,
        resolve: {
            plantType: PlantTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.plantType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plantTypePopupRoute: Routes = [
    {
        path: 'plant-type/:id/delete',
        component: PlantTypeDeletePopupComponent,
        resolve: {
            plantType: PlantTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.plantType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
