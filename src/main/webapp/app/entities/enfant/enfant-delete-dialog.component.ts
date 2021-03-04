import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnfant } from 'app/shared/model/enfant.model';
import { EnfantService } from './enfant.service';

@Component({
  templateUrl: './enfant-delete-dialog.component.html',
})
export class EnfantDeleteDialogComponent {
  enfant?: IEnfant;

  constructor(protected enfantService: EnfantService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.enfantService.delete(id).subscribe(() => {
      this.eventManager.broadcast('enfantListModification');
      this.activeModal.close();
    });
  }
}
