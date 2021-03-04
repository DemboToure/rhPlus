import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEnfant, Enfant } from 'app/shared/model/enfant.model';
import { EnfantService } from './enfant.service';
import { EnfantComponent } from './enfant.component';
import { EnfantDetailComponent } from './enfant-detail.component';
import { EnfantUpdateComponent } from './enfant-update.component';

@Injectable({ providedIn: 'root' })
export class EnfantResolve implements Resolve<IEnfant> {
  constructor(private service: EnfantService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEnfant> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((enfant: HttpResponse<Enfant>) => {
          if (enfant.body) {
            return of(enfant.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Enfant());
  }
}

export const enfantRoute: Routes = [
  {
    path: '',
    component: EnfantComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Enfants',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EnfantDetailComponent,
    resolve: {
      enfant: EnfantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Enfants',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EnfantUpdateComponent,
    resolve: {
      enfant: EnfantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Enfants',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EnfantUpdateComponent,
    resolve: {
      enfant: EnfantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Enfants',
    },
    canActivate: [UserRouteAccessService],
  },
];
