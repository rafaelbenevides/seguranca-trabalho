/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { TrainingItemComponent } from 'app/entities/training-item/training-item.component';
import { TrainingItemService } from 'app/entities/training-item/training-item.service';
import { TrainingItem } from 'app/shared/model/training-item.model';

describe('Component Tests', () => {
    describe('TrainingItem Management Component', () => {
        let comp: TrainingItemComponent;
        let fixture: ComponentFixture<TrainingItemComponent>;
        let service: TrainingItemService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [TrainingItemComponent],
                providers: []
            })
                .overrideTemplate(TrainingItemComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TrainingItemComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrainingItemService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new TrainingItem(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.trainingItems[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
