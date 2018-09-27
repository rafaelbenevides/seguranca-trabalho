import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrainingItem } from 'app/shared/model/training-item.model';

@Component({
    selector: 'jhi-training-item-detail',
    templateUrl: './training-item-detail.component.html'
})
export class TrainingItemDetailComponent implements OnInit {
    trainingItem: ITrainingItem;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ trainingItem }) => {
            this.trainingItem = trainingItem;
        });
    }

    previousState() {
        window.history.back();
    }
}
