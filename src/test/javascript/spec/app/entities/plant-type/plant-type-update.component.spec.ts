/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { PlantTypeUpdateComponent } from 'app/entities/plant-type/plant-type-update.component';
import { PlantTypeService } from 'app/entities/plant-type/plant-type.service';
import { PlantType } from 'app/shared/model/plant-type.model';

describe('Component Tests', () => {
    describe('PlantType Management Update Component', () => {
        let comp: PlantTypeUpdateComponent;
        let fixture: ComponentFixture<PlantTypeUpdateComponent>;
        let service: PlantTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [PlantTypeUpdateComponent]
            })
                .overrideTemplate(PlantTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PlantTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlantTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PlantType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.plantType = entity;
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
                    const entity = new PlantType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.plantType = entity;
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
