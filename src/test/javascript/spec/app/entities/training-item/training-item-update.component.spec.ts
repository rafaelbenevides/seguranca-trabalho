/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { TrainingItemUpdateComponent } from 'app/entities/training-item/training-item-update.component';
import { TrainingItemService } from 'app/entities/training-item/training-item.service';
import { TrainingItem } from 'app/shared/model/training-item.model';

describe('Component Tests', () => {
    describe('TrainingItem Management Update Component', () => {
        let comp: TrainingItemUpdateComponent;
        let fixture: ComponentFixture<TrainingItemUpdateComponent>;
        let service: TrainingItemService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [TrainingItemUpdateComponent]
            })
                .overrideTemplate(TrainingItemUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TrainingItemUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrainingItemService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TrainingItem(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.trainingItem = entity;
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
                    const entity = new TrainingItem();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.trainingItem = entity;
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
