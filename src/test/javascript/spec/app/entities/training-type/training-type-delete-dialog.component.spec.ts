/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { TrainingTypeDeleteDialogComponent } from 'app/entities/training-type/training-type-delete-dialog.component';
import { TrainingTypeService } from 'app/entities/training-type/training-type.service';

describe('Component Tests', () => {
    describe('TrainingType Management Delete Component', () => {
        let comp: TrainingTypeDeleteDialogComponent;
        let fixture: ComponentFixture<TrainingTypeDeleteDialogComponent>;
        let service: TrainingTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [TrainingTypeDeleteDialogComponent]
            })
                .overrideTemplate(TrainingTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TrainingTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrainingTypeService);
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
