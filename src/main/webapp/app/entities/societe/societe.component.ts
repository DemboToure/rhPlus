import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISociete } from 'app/shared/model/societe.model';
import { SocieteService } from './societe.service';
import { SocieteDeleteDialogComponent } from './societe-delete-dialog.component';

@Component({
  selector: 'jhi-societe',
  templateUrl: './societe.component.html',
})
export class SocieteComponent implements OnInit, OnDestroy {
  societes?: ISociete[];
  eventSubscriber?: Subscription;

  constructor(protected societeService: SocieteService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.societeService.query().subscribe((res: HttpResponse<ISociete[]>) => (this.societes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSocietes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISociete): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSocietes(): void {
    this.eventSubscriber = this.eventManager.subscribe('societeListModification', () => this.loadAll());
  }

  delete(societe: ISociete): void {
    const modalRef = this.modalService.open(SocieteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.societe = societe;
  }
}
