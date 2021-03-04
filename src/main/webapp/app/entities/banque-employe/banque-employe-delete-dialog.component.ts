import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBanqueEmploye } from 'app/shared/model/banque-employe.model';
import { BanqueEmployeService } from './banque-employe.service';

@Component({
  templateUrl: './banque-employe-delete-dialog.component.html',
})
export class BanqueEmployeDeleteDialogComponent {
  banqueEmploye?: IBanqueEmploye;

  constructor(
    protected banqueEmployeService: BanqueEmployeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.banqueEmployeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('banqueEmployeListModification');
      this.activeModal.close();
    });
  }
}
