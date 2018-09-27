import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Plant } from 'app/shared/model/plant.model';
import { PlantService } from './plant.service';
import { PlantComponent } from './plant.component';
import { PlantDetailComponent } from './plant-detail.component';
import { PlantUpdateComponent } from './plant-update.component';
import { PlantDeletePopupComponent } from './plant-delete-dialog.component';
import { IPlant } from 'app/shared/model/plant.model';

@Injectable({ providedIn: 'root' })
export class PlantResolve implements Resolve<IPlant> {
    constructor(private service: PlantService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((plant: HttpResponse<Plant>) => plant.body));
        }
        return of(new Plant());
    }
}

export const plantRoute: Routes = [
    {
        path: 'plant',
        component: PlantComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.plant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plant/:id/view',
        component: PlantDetailComponent,
        resolve: {
            plant: PlantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.plant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plant/new',
        component: PlantUpdateComponent,
        resolve: {
            plant: PlantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.plant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plant/:id/edit',
        component: PlantUpdateComponent,
        resolve: {
            plant: PlantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.plant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const plantPopupRoute: Routes = [
    {
        path: 'plant/:id/delete',
        component: PlantDeletePopupComponent,
        resolve: {
            plant: PlantResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'segurancatrabalhoApp.plant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
