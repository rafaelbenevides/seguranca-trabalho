import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPlant } from 'app/shared/model/plant.model';
import { Principal } from 'app/core';
import { PlantService } from './plant.service';

@Component({
    selector: 'jhi-plant',
    templateUrl: './plant.component.html'
})
export class PlantComponent implements OnInit, OnDestroy {
    plants: IPlant[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private plantService: PlantService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.plantService.query().subscribe(
            (res: HttpResponse<IPlant[]>) => {
                this.plants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPlants();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPlant) {
        return item.id;
    }

    registerChangeInPlants() {
        this.eventSubscriber = this.eventManager.subscribe('plantListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
