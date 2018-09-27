import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPlantType } from 'app/shared/model/plant-type.model';

type EntityResponseType = HttpResponse<IPlantType>;
type EntityArrayResponseType = HttpResponse<IPlantType[]>;

@Injectable({ providedIn: 'root' })
export class PlantTypeService {
    private resourceUrl = SERVER_API_URL + 'api/plant-types';

    constructor(private http: HttpClient) {}

    create(plantType: IPlantType): Observable<EntityResponseType> {
        return this.http.post<IPlantType>(this.resourceUrl, plantType, { observe: 'response' });
    }

    update(plantType: IPlantType): Observable<EntityResponseType> {
        return this.http.put<IPlantType>(this.resourceUrl, plantType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPlantType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPlantType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
