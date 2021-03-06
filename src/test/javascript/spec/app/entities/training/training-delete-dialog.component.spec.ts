/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { TrainingDeleteDialogComponent } from 'app/entities/training/training-delete-dialog.component';
import { TrainingService } from 'app/entities/training/training.service';

describe('Component Tests', () => {
    describe('Training Management Delete Component', () => {
        let comp: TrainingDeleteDialogComponent;
        let fixture: ComponentFixture<TrainingDeleteDialogComponent>;
        let service: TrainingService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [TrainingDeleteDialogComponent]
            })
                .overrideTemplate(TrainingDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TrainingDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrainingService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
