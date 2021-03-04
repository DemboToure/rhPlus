import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEpoux } from 'app/shared/model/epoux.model';

type EntityResponseType = HttpResponse<IEpoux>;
type EntityArrayResponseType = HttpResponse<IEpoux[]>;

@Injectable({ providedIn: 'root' })
export class EpouxService {
  public resourceUrl = SERVER_API_URL + 'api/epouxes';

  constructor(protected http: HttpClient) {}

  create(epoux: IEpoux): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(epoux);
    return this.http
      .post<IEpoux>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(epoux: IEpoux): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(epoux);
    return this.http
      .put<IEpoux>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEpoux>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEpoux[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(epoux: IEpoux): IEpoux {
    const copy: IEpoux = Object.assign({}, epoux, {
      dateNaissance: epoux.dateNaissance && epoux.dateNaissance.isValid() ? epoux.dateNaissance.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateNaissance = res.body.dateNaissance ? moment(res.body.dateNaissance) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((epoux: IEpoux) => {
        epoux.dateNaissance = epoux.dateNaissance ? moment(epoux.dateNaissance) : undefined;
      });
    }
    return res;
  }
}
