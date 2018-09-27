import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJobRole } from 'app/shared/model/job-role.model';

type EntityResponseType = HttpResponse<IJobRole>;
type EntityArrayResponseType = HttpResponse<IJobRole[]>;

@Injectable({ providedIn: 'root' })
export class JobRoleService {
    private resourceUrl = SERVER_API_URL + 'api/job-roles';

    constructor(private http: HttpClient) {}

    create(jobRole: IJobRole): Observable<EntityResponseType> {
        return this.http.post<IJobRole>(this.resourceUrl, jobRole, { observe: 'response' });
    }

    update(jobRole: IJobRole): Observable<EntityResponseType> {
        return this.http.put<IJobRole>(this.resourceUrl, jobRole, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IJobRole>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IJobRole[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
