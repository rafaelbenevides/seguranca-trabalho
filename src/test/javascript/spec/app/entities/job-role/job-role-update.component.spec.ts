/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { JobRoleUpdateComponent } from 'app/entities/job-role/job-role-update.component';
import { JobRoleService } from 'app/entities/job-role/job-role.service';
import { JobRole } from 'app/shared/model/job-role.model';

describe('Component Tests', () => {
    describe('JobRole Management Update Component', () => {
        let comp: JobRoleUpdateComponent;
        let fixture: ComponentFixture<JobRoleUpdateComponent>;
        let service: JobRoleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [JobRoleUpdateComponent]
            })
                .overrideTemplate(JobRoleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(JobRoleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobRoleService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new JobRole(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.jobRole = entity;
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
                    const entity = new JobRole();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.jobRole = entity;
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
