import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServiceEntreprise } from 'app/shared/model/service-entreprise.model';

@Component({
  selector: 'jhi-service-entreprise-detail',
  templateUrl: './service-entreprise-detail.component.html',
})
export class ServiceEntrepriseDetailComponent implements OnInit {
  serviceEntreprise: IServiceEntreprise | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ serviceEntreprise }) => (this.serviceEntreprise = serviceEntreprise));
  }

  previousState(): void {
    window.history.back();
  }
}
