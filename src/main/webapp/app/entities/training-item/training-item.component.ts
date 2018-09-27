import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ITrainingItem } from 'app/shared/model/training-item.model';
import { Principal } from 'app/core';
import { TrainingItemService } from './training-item.service';

@Component({
    selector: 'jhi-training-item',
    templateUrl: './training-item.component.html'
})
export class TrainingItemComponent implements OnInit, OnDestroy {
    trainingItems: ITrainingItem[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private trainingItemService: TrainingItemService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.trainingItemService.query().subscribe(
            (res: HttpResponse<ITrainingItem[]>) => {
                this.trainingItems = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTrainingItems();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITrainingItem) {
        return item.id;
    }

    registerChangeInTrainingItems() {
        this.eventSubscriber = this.eventManager.subscribe('trainingItemListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
