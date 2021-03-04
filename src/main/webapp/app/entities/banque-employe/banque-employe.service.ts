import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBanqueEmploye } from 'app/shared/model/banque-employe.model';

type EntityResponseType = HttpResponse<IBanqueEmploye>;
type EntityArrayResponseType = HttpResponse<IBanqueEmploye[]>;

@Injectable({ providedIn: 'root' })
export class BanqueEmployeService {
  public resourceUrl = SERVER_API_URL + 'api/banque-employes';

  constructor(protected http: HttpClient) {}

  create(banqueEmploye: IBanqueEmploye): Observable<EntityResponseType> {
    return this.http.post<IBanqueEmploye>(this.resourceUrl, banqueEmploye, { observe: 'response' });
  }

  update(banqueEmploye: IBanqueEmploye): Observable<EntityResponseType> {
    return this.http.put<IBanqueEmploye>(this.resourceUrl, banqueEmploye, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBanqueEmploye>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBanqueEmploye[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
