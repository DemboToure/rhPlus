import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IServiceEntreprise, ServiceEntreprise } from 'app/shared/model/service-entreprise.model';
import { ServiceEntrepriseService } from './service-entreprise.service';
import { ServiceEntrepriseComponent } from './service-entreprise.component';
import { ServiceEntrepriseDetailComponent } from './service-entreprise-detail.component';
import { ServiceEntrepriseUpdateComponent } from './service-entreprise-update.component';

@Injectable({ providedIn: 'root' })
export class ServiceEntrepriseResolve implements Resolve<IServiceEntreprise> {
  constructor(private service: ServiceEntrepriseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IServiceEntreprise> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((serviceEntreprise: HttpResponse<ServiceEntreprise>) => {
          if (serviceEntreprise.body) {
            return of(serviceEntreprise.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ServiceEntreprise());
  }
}

export const serviceEntrepriseRoute: Routes = [
  {
    path: '',
    component: ServiceEntrepriseComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ServiceEntreprises',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ServiceEntrepriseDetailComponent,
    resolve: {
      serviceEntreprise: ServiceEntrepriseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ServiceEntreprises',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ServiceEntrepriseUpdateComponent,
    resolve: {
      serviceEntreprise: ServiceEntrepriseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ServiceEntreprises',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ServiceEntrepriseUpdateComponent,
    resolve: {
      serviceEntreprise: ServiceEntrepriseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ServiceEntreprises',
    },
    canActivate: [UserRouteAccessService],
  },
];
