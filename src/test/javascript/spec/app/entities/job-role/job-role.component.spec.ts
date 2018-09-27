/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { JobRoleComponent } from 'app/entities/job-role/job-role.component';
import { JobRoleService } from 'app/entities/job-role/job-role.service';
import { JobRole } from 'app/shared/model/job-role.model';

describe('Component Tests', () => {
    describe('JobRole Management Component', () => {
        let comp: JobRoleComponent;
        let fixture: ComponentFixture<JobRoleComponent>;
        let service: JobRoleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [JobRoleComponent],
                providers: []
            })
                .overrideTemplate(JobRoleComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(JobRoleComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JobRoleService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new JobRole(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.jobRoles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
