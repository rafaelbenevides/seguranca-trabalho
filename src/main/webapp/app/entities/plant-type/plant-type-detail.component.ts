import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPlantType } from 'app/shared/model/plant-type.model';

@Component({
    selector: 'jhi-plant-type-detail',
    templateUrl: './plant-type-detail.component.html'
})
export class PlantTypeDetailComponent implements OnInit {
    plantType: IPlantType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ plantType }) => {
            this.plantType = plantType;
        });
    }

    previousState() {
        window.history.back();
    }
}
