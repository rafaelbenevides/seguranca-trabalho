import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrainingType } from 'app/shared/model/training-type.model';

@Component({
    selector: 'jhi-training-type-detail',
    templateUrl: './training-type-detail.component.html'
})
export class TrainingTypeDetailComponent implements OnInit {
    trainingType: ITrainingType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ trainingType }) => {
            this.trainingType = trainingType;
        });
    }

    previousState() {
        window.history.back();
    }
}
