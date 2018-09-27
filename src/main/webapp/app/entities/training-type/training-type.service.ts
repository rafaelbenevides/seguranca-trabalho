import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITrainingType } from 'app/shared/model/training-type.model';

type EntityResponseType = HttpResponse<ITrainingType>;
type EntityArrayResponseType = HttpResponse<ITrainingType[]>;

@Injectable({ providedIn: 'root' })
export class TrainingTypeService {
    private resourceUrl = SERVER_API_URL + 'api/training-types';

    constructor(private http: HttpClient) {}

    create(trainingType: ITrainingType): Observable<EntityResponseType> {
        return this.http.post<ITrainingType>(this.resourceUrl, trainingType, { observe: 'response' });
    }

    update(trainingType: ITrainingType): Observable<EntityResponseType> {
        return this.http.put<ITrainingType>(this.resourceUrl, trainingType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITrainingType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITrainingType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
