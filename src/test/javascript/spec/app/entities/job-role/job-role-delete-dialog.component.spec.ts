/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { JobRoleDeleteDialogComponent } from 'app/entities/job-role/job-role-delete-dialog.component';
import { JobRoleService } from 'app/entities/job-role/job-role.service';

describe('Component Tests', () => {
    describe('JobRole Management Delete Component', () => {
        let comp: JobRoleDeleteDialogComponent;
        let fixture: ComponentFixture<JobRoleDeleteDialogComponent>;
        let service: JobRoleService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [JobRoleDeleteDialogComponent]
            })
                .overrideTemplate(JobRoleDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(JobRoleDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobRoleService);
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
