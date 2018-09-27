/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { PlantDeleteDialogComponent } from 'app/entities/plant/plant-delete-dialog.component';
import { PlantService } from 'app/entities/plant/plant.service';

describe('Component Tests', () => {
    describe('Plant Management Delete Component', () => {
        let comp: PlantDeleteDialogComponent;
        let fixture: ComponentFixture<PlantDeleteDialogComponent>;
        let service: PlantService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [PlantDeleteDialogComponent]
            })
                .overrideTemplate(PlantDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlantDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlantService);
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
