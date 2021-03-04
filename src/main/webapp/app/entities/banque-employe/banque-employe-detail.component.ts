import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBanqueEmploye } from 'app/shared/model/banque-employe.model';

@Component({
  selector: 'jhi-banque-employe-detail',
  templateUrl: './banque-employe-detail.component.html',
})
export class BanqueEmployeDetailComponent implements OnInit {
  banqueEmploye: IBanqueEmploye | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ banqueEmploye }) => (this.banqueEmploye = banqueEmploye));
  }

  previousState(): void {
    window.history.back();
  }
}
