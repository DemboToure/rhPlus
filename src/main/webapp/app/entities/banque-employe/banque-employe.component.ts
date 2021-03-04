import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBanqueEmploye } from 'app/shared/model/banque-employe.model';
import { BanqueEmployeService } from './banque-employe.service';
import { BanqueEmployeDeleteDialogComponent } from './banque-employe-delete-dialog.component';

@Component({
  selector: 'jhi-banque-employe',
  templateUrl: './banque-employe.component.html',
})
export class BanqueEmployeComponent implements OnInit, OnDestroy {
  banqueEmployes?: IBanqueEmploye[];
  eventSubscriber?: Subscription;

  constructor(
    protected banqueEmployeService: BanqueEmployeService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.banqueEmployeService.query().subscribe((res: HttpResponse<IBanqueEmploye[]>) => (this.banqueEmployes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBanqueEmployes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBanqueEmploye): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBanqueEmployes(): void {
    this.eventSubscriber = this.eventManager.subscribe('banqueEmployeListModification', () => this.loadAll());
  }

  delete(banqueEmploye: IBanqueEmploye): void {
    const modalRef = this.modalService.open(BanqueEmployeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.banqueEmploye = banqueEmploye;
  }
}
