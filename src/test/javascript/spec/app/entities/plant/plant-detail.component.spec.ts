/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { PlantDetailComponent } from 'app/entities/plant/plant-detail.component';
import { Plant } from 'app/shared/model/plant.model';

describe('Component Tests', () => {
    describe('Plant Management Detail Component', () => {
        let comp: PlantDetailComponent;
        let fixture: ComponentFixture<PlantDetailComponent>;
        const route = ({ data: of({ plant: new Plant(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [PlantDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlantDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlantDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.plant).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
