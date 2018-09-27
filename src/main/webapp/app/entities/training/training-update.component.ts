import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITraining } from 'app/shared/model/training.model';
import { TrainingService } from './training.service';
import { IPlant } from 'app/shared/model/plant.model';
import { PlantService } from 'app/entities/plant';
import { IPlantType } from 'app/shared/model/plant-type.model';
import { PlantTypeService } from 'app/entities/plant-type';
import { IEmployee } from 'app/shared/model/employee.model';
import { EmployeeService } from 'app/entities/employee';

@Component({
    selector: 'jhi-training-update',
    templateUrl: './training-update.component.html'
})
export class TrainingUpdateComponent implements OnInit {
    private _training: ITraining;
    isSaving: boolean;

    plants: IPlant[];

    planttypes: IPlantType[];

    employees: IEmployee[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private trainingService: TrainingService,
        private plantService: PlantService,
        private plantTypeService: PlantTypeService,
        private employeeService: EmployeeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ training }) => {
            this.training = training;
        });
        this.plantService.query().subscribe(
            (res: HttpResponse<IPlant[]>) => {
                this.plants = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.plantTypeService.query().subscribe(
            (res: HttpResponse<IPlantType[]>) => {
                this.planttypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.employeeService.query().subscribe(
            (res: HttpResponse<IEmployee[]>) => {
                this.employees = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.training.id !== undefined) {
            this.subscribeToSaveResponse(this.trainingService.update(this.training));
        } else {
            this.subscribeToSaveResponse(this.trainingService.create(this.training));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITraining>>) {
        result.subscribe((res: HttpResponse<ITraining>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackPlantById(index: number, item: IPlant) {
        return item.id;
    }

    trackPlantTypeById(index: number, item: IPlantType) {
        return item.id;
    }

    trackEmployeeById(index: number, item: IEmployee) {
        return item.id;
    }
    get training() {
        return this._training;
    }

    set training(training: ITraining) {
        this._training = training;
    }
}
