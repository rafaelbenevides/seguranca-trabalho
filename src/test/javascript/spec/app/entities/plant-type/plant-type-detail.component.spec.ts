/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { PlantTypeDetailComponent } from 'app/entities/plant-type/plant-type-detail.component';
import { PlantType } from 'app/shared/model/plant-type.model';

describe('Component Tests', () => {
    describe('PlantType Management Detail Component', () => {
        let comp: PlantTypeDetailComponent;
        let fixture: ComponentFixture<PlantTypeDetailComponent>;
        const route = ({ data: of({ plantType: new PlantType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [PlantTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PlantTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlantTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.plantType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
