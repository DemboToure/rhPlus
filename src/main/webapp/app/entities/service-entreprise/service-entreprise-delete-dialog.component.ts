import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IServiceEntreprise } from 'app/shared/model/service-entreprise.model';
import { ServiceEntrepriseService } from './service-entreprise.service';

@Component({
  templateUrl: './service-entreprise-delete-dialog.component.html',
})
export class ServiceEntrepriseDeleteDialogComponent {
  serviceEntreprise?: IServiceEntreprise;

  constructor(
    protected serviceEntrepriseService: ServiceEntrepriseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.serviceEntrepriseService.delete(id).subscribe(() => {
      this.eventManager.broadcast('serviceEntrepriseListModification');
      this.activeModal.close();
    });
  }
}
