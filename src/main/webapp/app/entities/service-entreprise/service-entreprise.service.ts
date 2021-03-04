import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IServiceEntreprise } from 'app/shared/model/service-entreprise.model';

type EntityResponseType = HttpResponse<IServiceEntreprise>;
type EntityArrayResponseType = HttpResponse<IServiceEntreprise[]>;

@Injectable({ providedIn: 'root' })
export class ServiceEntrepriseService {
  public resourceUrl = SERVER_API_URL + 'api/service-entreprises';

  constructor(protected http: HttpClient) {}

  create(serviceEntreprise: IServiceEntreprise): Observable<EntityResponseType> {
    return this.http.post<IServiceEntreprise>(this.resourceUrl, serviceEntreprise, { observe: 'response' });
  }

  update(serviceEntreprise: IServiceEntreprise): Observable<EntityResponseType> {
    return this.http.put<IServiceEntreprise>(this.resourceUrl, serviceEntreprise, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IServiceEntreprise>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IServiceEntreprise[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
