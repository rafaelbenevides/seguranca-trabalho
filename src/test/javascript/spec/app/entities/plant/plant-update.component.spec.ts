/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { PlantUpdateComponent } from 'app/entities/plant/plant-update.component';
import { PlantService } from 'app/entities/plant/plant.service';
import { Plant } from 'app/shared/model/plant.model';

describe('Component Tests', () => {
    describe('Plant Management Update Component', () => {
        let comp: PlantUpdateComponent;
        let fixture: ComponentFixture<PlantUpdateComponent>;
        let service: PlantService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [PlantUpdateComponent]
            })
                .overrideTemplate(PlantUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlantUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlantService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Plant(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.plant = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Plant();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.plant = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
