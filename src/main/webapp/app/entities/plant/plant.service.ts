import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlant } from 'app/shared/model/plant.model';

type EntityResponseType = HttpResponse<IPlant>;
type EntityArrayResponseType = HttpResponse<IPlant[]>;

@Injectable({ providedIn: 'root' })
export class PlantService {
    private resourceUrl = SERVER_API_URL + 'api/plants';

    constructor(private http: HttpClient) {}

    create(plant: IPlant): Observable<EntityResponseType> {
        return this.http.post<IPlant>(this.resourceUrl, plant, { observe: 'response' });
    }

    update(plant: IPlant): Observable<EntityResponseType> {
        return this.http.put<IPlant>(this.resourceUrl, plant, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlant>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlant[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
