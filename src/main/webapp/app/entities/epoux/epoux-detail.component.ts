import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEpoux } from 'app/shared/model/epoux.model';

@Component({
  selector: 'jhi-epoux-detail',
  templateUrl: './epoux-detail.component.html',
})
export class EpouxDetailComponent implements OnInit {
  epoux: IEpoux | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ epoux }) => (this.epoux = epoux));
  }

  previousState(): void {
    window.history.back();
  }
}
