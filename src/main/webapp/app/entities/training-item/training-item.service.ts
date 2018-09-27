import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITrainingItem } from 'app/shared/model/training-item.model';

type EntityResponseType = HttpResponse<ITrainingItem>;
type EntityArrayResponseType = HttpResponse<ITrainingItem[]>;

@Injectable({ providedIn: 'root' })
export class TrainingItemService {
    private resourceUrl = SERVER_API_URL + 'api/training-items';

    constructor(private http: HttpClient) {}

    create(trainingItem: ITrainingItem): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(trainingItem);
        return this.http
            .post<ITrainingItem>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(trainingItem: ITrainingItem): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(trainingItem);
        return this.http
            .put<ITrainingItem>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITrainingItem>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITrainingItem[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(trainingItem: ITrainingItem): ITrainingItem {
        const copy: ITrainingItem = Object.assign({}, trainingItem, {
            date: trainingItem.date != null && trainingItem.date.isValid() ? trainingItem.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.date = res.body.date != null ? moment(res.body.date) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((trainingItem: ITrainingItem) => {
            trainingItem.date = trainingItem.date != null ? moment(trainingItem.date) : null;
        });
        return res;
    }
}
