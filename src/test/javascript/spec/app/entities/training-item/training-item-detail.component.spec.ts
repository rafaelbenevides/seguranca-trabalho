/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { TrainingItemDetailComponent } from 'app/entities/training-item/training-item-detail.component';
import { TrainingItem } from 'app/shared/model/training-item.model';

describe('Component Tests', () => {
    describe('TrainingItem Management Detail Component', () => {
        let comp: TrainingItemDetailComponent;
        let fixture: ComponentFixture<TrainingItemDetailComponent>;
        const route = ({ data: of({ trainingItem: new TrainingItem(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [TrainingItemDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TrainingItemDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TrainingItemDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.trainingItem).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
