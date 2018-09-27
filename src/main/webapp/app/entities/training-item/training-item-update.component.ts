import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITrainingItem } from 'app/shared/model/training-item.model';
import { TrainingItemService } from './training-item.service';
import { ITraining } from 'app/shared/model/training.model';
import { TrainingService } from 'app/entities/training';
import { ITrainingType } from 'app/shared/model/training-type.model';
import { TrainingTypeService } from 'app/entities/training-type';

@Component({
    selector: 'jhi-training-item-update',
    templateUrl: './training-item-update.component.html'
})
export class TrainingItemUpdateComponent implements OnInit {
    private _trainingItem: ITrainingItem;
    isSaving: boolean;

    trainings: ITraining[];

    trainingtypes: ITrainingType[];
    dateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private trainingItemService: TrainingItemService,
        private trainingService: TrainingService,
        private trainingTypeService: TrainingTypeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ trainingItem }) => {
            this.trainingItem = trainingItem;
        });
        this.trainingService.query().subscribe(
            (res: HttpResponse<ITraining[]>) => {
                this.trainings = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.trainingTypeService.query().subscribe(
            (res: HttpResponse<ITrainingType[]>) => {
                this.trainingtypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.trainingItem.id !== undefined) {
            this.subscribeToSaveResponse(this.trainingItemService.update(this.trainingItem));
        } else {
            this.subscribeToSaveResponse(this.trainingItemService.create(this.trainingItem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITrainingItem>>) {
        result.subscribe((res: HttpResponse<ITrainingItem>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackTrainingById(index: number, item: ITraining) {
        return item.id;
    }

    trackTrainingTypeById(index: number, item: ITrainingType) {
        return item.id;
    }
    get trainingItem() {
        return this._trainingItem;
    }

    set trainingItem(trainingItem: ITrainingItem) {
        this._trainingItem = trainingItem;
    }
}
