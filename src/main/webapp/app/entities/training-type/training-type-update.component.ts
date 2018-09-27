import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITrainingType } from 'app/shared/model/training-type.model';
import { TrainingTypeService } from './training-type.service';

@Component({
    selector: 'jhi-training-type-update',
    templateUrl: './training-type-update.component.html'
})
export class TrainingTypeUpdateComponent implements OnInit {
    private _trainingType: ITrainingType;
    isSaving: boolean;

    constructor(private trainingTypeService: TrainingTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ trainingType }) => {
            this.trainingType = trainingType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.trainingType.id !== undefined) {
            this.subscribeToSaveResponse(this.trainingTypeService.update(this.trainingType));
        } else {
            this.subscribeToSaveResponse(this.trainingTypeService.create(this.trainingType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITrainingType>>) {
        result.subscribe((res: HttpResponse<ITrainingType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get trainingType() {
        return this._trainingType;
    }

    set trainingType(trainingType: ITrainingType) {
        this._trainingType = trainingType;
    }
}
