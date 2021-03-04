import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEnfant } from 'app/shared/model/enfant.model';
import { EnfantService } from './enfant.service';
import { EnfantDeleteDialogComponent } from './enfant-delete-dialog.component';

@Component({
  selector: 'jhi-enfant',
  templateUrl: './enfant.component.html',
})
export class EnfantComponent implements OnInit, OnDestroy {
  enfants?: IEnfant[];
  eventSubscriber?: Subscription;

  constructor(protected enfantService: EnfantService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.enfantService.query().subscribe((res: HttpResponse<IEnfant[]>) => (this.enfants = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEnfants();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEnfant): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEnfants(): void {
    this.eventSubscriber = this.eventManager.subscribe('enfantListModification', () => this.loadAll());
  }

  delete(enfant: IEnfant): void {
    const modalRef = this.modalService.open(EnfantDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.enfant = enfant;
  }
}
