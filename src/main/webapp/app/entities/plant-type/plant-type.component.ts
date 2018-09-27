import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IPlantType } from 'app/shared/model/plant-type.model';
import { Principal } from 'app/core';
import { PlantTypeService } from './plant-type.service';

@Component({
    selector: 'jhi-plant-type',
    templateUrl: './plant-type.component.html'
})
export class PlantTypeComponent implements OnInit, OnDestroy {
    plantTypes: IPlantType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private plantTypeService: PlantTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.plantTypeService.query().subscribe(
            (res: HttpResponse<IPlantType[]>) => {
                this.plantTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInPlantTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IPlantType) {
        return item.id;
    }

    registerChangeInPlantTypes() {
        this.eventSubscriber = this.eventManager.subscribe('plantTypeListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
