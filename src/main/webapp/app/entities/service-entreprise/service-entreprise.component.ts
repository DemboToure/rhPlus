import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IServiceEntreprise } from 'app/shared/model/service-entreprise.model';
import { ServiceEntrepriseService } from './service-entreprise.service';
import { ServiceEntrepriseDeleteDialogComponent } from './service-entreprise-delete-dialog.component';

@Component({
  selector: 'jhi-service-entreprise',
  templateUrl: './service-entreprise.component.html',
})
export class ServiceEntrepriseComponent implements OnInit, OnDestroy {
  serviceEntreprises?: IServiceEntreprise[];
  eventSubscriber?: Subscription;

  constructor(
    protected serviceEntrepriseService: ServiceEntrepriseService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.serviceEntrepriseService
      .query()
      .subscribe((res: HttpResponse<IServiceEntreprise[]>) => (this.serviceEntreprises = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInServiceEntreprises();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IServiceEntreprise): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInServiceEntreprises(): void {
    this.eventSubscriber = this.eventManager.subscribe('serviceEntrepriseListModification', () => this.loadAll());
  }

  delete(serviceEntreprise: IServiceEntreprise): void {
    const modalRef = this.modalService.open(ServiceEntrepriseDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.serviceEntreprise = serviceEntreprise;
  }
}
