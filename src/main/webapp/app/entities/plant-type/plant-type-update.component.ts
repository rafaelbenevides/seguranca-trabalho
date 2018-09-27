import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPlantType } from 'app/shared/model/plant-type.model';
import { PlantTypeService } from './plant-type.service';

@Component({
    selector: 'jhi-plant-type-update',
    templateUrl: './plant-type-update.component.html'
})
export class PlantTypeUpdateComponent implements OnInit {
    private _plantType: IPlantType;
    isSaving: boolean;

    constructor(private plantTypeService: PlantTypeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plantType }) => {
            this.plantType = plantType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plantType.id !== undefined) {
            this.subscribeToSaveResponse(this.plantTypeService.update(this.plantType));
        } else {
            this.subscribeToSaveResponse(this.plantTypeService.create(this.plantType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPlantType>>) {
        result.subscribe((res: HttpResponse<IPlantType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get plantType() {
        return this._plantType;
    }

    set plantType(plantType: IPlantType) {
        this._plantType = plantType;
    }
}
