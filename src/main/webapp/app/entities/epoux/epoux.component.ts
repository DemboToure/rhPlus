import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEpoux } from 'app/shared/model/epoux.model';
import { EpouxService } from './epoux.service';
import { EpouxDeleteDialogComponent } from './epoux-delete-dialog.component';

@Component({
  selector: 'jhi-epoux',
  templateUrl: './epoux.component.html',
})
export class EpouxComponent implements OnInit, OnDestroy {
  epouxes?: IEpoux[];
  eventSubscriber?: Subscription;

  constructor(protected epouxService: EpouxService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.epouxService.query().subscribe((res: HttpResponse<IEpoux[]>) => (this.epouxes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEpouxes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEpoux): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEpouxes(): void {
    this.eventSubscriber = this.eventManager.subscribe('epouxListModification', () => this.loadAll());
  }

  delete(epoux: IEpoux): void {
    const modalRef = this.modalService.open(EpouxDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.epoux = epoux;
  }
}
