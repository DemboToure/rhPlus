import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBanqueEmploye, BanqueEmploye } from 'app/shared/model/banque-employe.model';
import { BanqueEmployeService } from './banque-employe.service';
import { IBanque } from 'app/shared/model/banque.model';
import { BanqueService } from 'app/entities/banque/banque.service';

@Component({
  selector: 'jhi-banque-employe-update',
  templateUrl: './banque-employe-update.component.html',
})
export class BanqueEmployeUpdateComponent implements OnInit {
  isSaving = false;
  banques: IBanque[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    numeroCompte: [null, [Validators.required]],
    codeBanque: [null, [Validators.required]],
    codeGuichet: [],
    cleRib: [],
    pdfDomiciliation: [],
    banque: [],
  });

  constructor(
    protected banqueEmployeService: BanqueEmployeService,
    protected banqueService: BanqueService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ banqueEmploye }) => {
      this.updateForm(banqueEmploye);

      this.banqueService.query().subscribe((res: HttpResponse<IBanque[]>) => (this.banques = res.body || []));
    });
  }

  updateForm(banqueEmploye: IBanqueEmploye): void {
    this.editForm.patchValue({
      id: banqueEmploye.id,
      nom: banqueEmploye.nom,
      numeroCompte: banqueEmploye.numeroCompte,
      codeBanque: banqueEmploye.codeBanque,
      codeGuichet: banqueEmploye.codeGuichet,
      cleRib: banqueEmploye.cleRib,
      pdfDomiciliation: banqueEmploye.pdfDomiciliation,
      banque: banqueEmploye.banque,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const banqueEmploye = this.createFromForm();
    if (banqueEmploye.id !== undefined) {
      this.subscribeToSaveResponse(this.banqueEmployeService.update(banqueEmploye));
    } else {
      this.subscribeToSaveResponse(this.banqueEmployeService.create(banqueEmploye));
    }
  }

  private createFromForm(): IBanqueEmploye {
    return {
      ...new BanqueEmploye(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      numeroCompte: this.editForm.get(['numeroCompte'])!.value,
      codeBanque: this.editForm.get(['codeBanque'])!.value,
      codeGuichet: this.editForm.get(['codeGuichet'])!.value,
      cleRib: this.editForm.get(['cleRib'])!.value,
      pdfDomiciliation: this.editForm.get(['pdfDomiciliation'])!.value,
      banque: this.editForm.get(['banque'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBanqueEmploye>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IBanque): any {
    return item.id;
  }
}
