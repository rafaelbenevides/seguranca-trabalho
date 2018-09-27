import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPlant } from 'app/shared/model/plant.model';
import { PlantService } from './plant.service';

@Component({
    selector: 'jhi-plant-update',
    templateUrl: './plant-update.component.html'
})
export class PlantUpdateComponent implements OnInit {
    private _plant: IPlant;
    isSaving: boolean;

    constructor(private plantService: PlantService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ plant }) => {
            this.plant = plant;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.plant.id !== undefined) {
            this.subscribeToSaveResponse(this.plantService.update(this.plant));
        } else {
            this.subscribeToSaveResponse(this.plantService.create(this.plant));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPlant>>) {
        result.subscribe((res: HttpResponse<IPlant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get plant() {
        return this._plant;
    }

    set plant(plant: IPlant) {
        this._plant = plant;
    }
}
