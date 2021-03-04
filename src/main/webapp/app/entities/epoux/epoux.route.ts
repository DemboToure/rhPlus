import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEpoux, Epoux } from 'app/shared/model/epoux.model';
import { EpouxService } from './epoux.service';
import { EpouxComponent } from './epoux.component';
import { EpouxDetailComponent } from './epoux-detail.component';
import { EpouxUpdateComponent } from './epoux-update.component';

@Injectable({ providedIn: 'root' })
export class EpouxResolve implements Resolve<IEpoux> {
  constructor(private service: EpouxService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEpoux> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((epoux: HttpResponse<Epoux>) => {
          if (epoux.body) {
            return of(epoux.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Epoux());
  }
}

export const epouxRoute: Routes = [
  {
    path: '',
    component: EpouxComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Epouxes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EpouxDetailComponent,
    resolve: {
      epoux: EpouxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Epouxes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EpouxUpdateComponent,
    resolve: {
      epoux: EpouxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Epouxes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EpouxUpdateComponent,
    resolve: {
      epoux: EpouxResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Epouxes',
    },
    canActivate: [UserRouteAccessService],
  },
];
