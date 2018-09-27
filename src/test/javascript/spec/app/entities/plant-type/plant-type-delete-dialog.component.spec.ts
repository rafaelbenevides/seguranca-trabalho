/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { PlantTypeDeleteDialogComponent } from 'app/entities/plant-type/plant-type-delete-dialog.component';
import { PlantTypeService } from 'app/entities/plant-type/plant-type.service';

describe('Component Tests', () => {
    describe('PlantType Management Delete Component', () => {
        let comp: PlantTypeDeleteDialogComponent;
        let fixture: ComponentFixture<PlantTypeDeleteDialogComponent>;
        let service: PlantTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [PlantTypeDeleteDialogComponent]
            })
                .overrideTemplate(PlantTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PlantTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PlantTypeService);
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
