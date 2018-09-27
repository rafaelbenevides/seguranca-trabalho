import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlant } from 'app/shared/model/plant.model';

@Component({
    selector: 'jhi-plant-detail',
    templateUrl: './plant-detail.component.html'
})
export class PlantDetailComponent implements OnInit {
    plant: IPlant;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plant }) => {
            this.plant = plant;
        });
    }

    previousState() {
        window.history.back();
    }
}
