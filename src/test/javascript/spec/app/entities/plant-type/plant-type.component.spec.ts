/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { PlantTypeComponent } from 'app/entities/plant-type/plant-type.component';
import { PlantTypeService } from 'app/entities/plant-type/plant-type.service';
import { PlantType } from 'app/shared/model/plant-type.model';

describe('Component Tests', () => {
    describe('PlantType Management Component', () => {
        let comp: PlantTypeComponent;
        let fixture: ComponentFixture<PlantTypeComponent>;
        let service: PlantTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [PlantTypeComponent],
                providers: []
            })
                .overrideTemplate(PlantTypeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlantTypeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlantTypeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new PlantType(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.plantTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
