import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEpoux } from 'app/shared/model/epoux.model';
import { EpouxService } from './epoux.service';

@Component({
  templateUrl: './epoux-delete-dialog.component.html',
})
export class EpouxDeleteDialogComponent {
  epoux?: IEpoux;

  constructor(protected epouxService: EpouxService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.epouxService.delete(id).subscribe(() => {
      this.eventManager.broadcast('epouxListModification');
      this.activeModal.close();
    });
  }
}
