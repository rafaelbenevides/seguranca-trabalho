/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { PlantComponent } from 'app/entities/plant/plant.component';
import { PlantService } from 'app/entities/plant/plant.service';
import { Plant } from 'app/shared/model/plant.model';

describe('Component Tests', () => {
    describe('Plant Management Component', () => {
        let comp: PlantComponent;
        let fixture: ComponentFixture<PlantComponent>;
        let service: PlantService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [PlantComponent],
                providers: []
            })
                .overrideTemplate(PlantComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlantComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlantService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Plant(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.plants[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
