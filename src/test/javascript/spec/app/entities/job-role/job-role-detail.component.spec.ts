/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SegurancatrabalhoTestModule } from '../../../test.module';
import { JobRoleDetailComponent } from 'app/entities/job-role/job-role-detail.component';
import { JobRole } from 'app/shared/model/job-role.model';

describe('Component Tests', () => {
    describe('JobRole Management Detail Component', () => {
        let comp: JobRoleDetailComponent;
        let fixture: ComponentFixture<JobRoleDetailComponent>;
        const route = ({ data: of({ jobRole: new JobRole(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SegurancatrabalhoTestModule],
                declarations: [JobRoleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(JobRoleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(JobRoleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.jobRole).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
