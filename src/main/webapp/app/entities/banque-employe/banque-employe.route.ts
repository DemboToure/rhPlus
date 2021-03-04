import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBanqueEmploye, BanqueEmploye } from 'app/shared/model/banque-employe.model';
import { BanqueEmployeService } from './banque-employe.service';
import { BanqueEmployeComponent } from './banque-employe.component';
import { BanqueEmployeDetailComponent } from './banque-employe-detail.component';
import { BanqueEmployeUpdateComponent } from './banque-employe-update.component';

@Injectable({ providedIn: 'root' })
export class BanqueEmployeResolve implements Resolve<IBanqueEmploye> {
  constructor(private service: BanqueEmployeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBanqueEmploye> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((banqueEmploye: HttpResponse<BanqueEmploye>) => {
          if (banqueEmploye.body) {
            return of(banqueEmploye.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BanqueEmploye());
  }
}

export const banqueEmployeRoute: Routes = [
  {
    path: '',
    component: BanqueEmployeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BanqueEmployes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BanqueEmployeDetailComponent,
    resolve: {
      banqueEmploye: BanqueEmployeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BanqueEmployes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BanqueEmployeUpdateComponent,
    resolve: {
      banqueEmploye: BanqueEmployeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BanqueEmployes',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BanqueEmployeUpdateComponent,
    resolve: {
      banqueEmploye: BanqueEmployeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BanqueEmployes',
    },
    canActivate: [UserRouteAccessService],
  },
];
