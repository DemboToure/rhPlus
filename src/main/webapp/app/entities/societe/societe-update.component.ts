import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISociete, Societe } from 'app/shared/model/societe.model';
import { SocieteService } from './societe.service';

@Component({
  selector: 'jhi-societe-update',
  templateUrl: './societe-update.component.html',
})
export class SocieteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [null, [Validators.required]],
    adresse: [null, [Validators.required]],
    telephone: [null, [Validators.required]],
    fixe: [null, [Validators.required]],
    ninea: [],
    logoUrl: [],
  });

  constructor(protected societeService: SocieteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ societe }) => {
      this.updateForm(societe);
    });
  }

  updateForm(societe: ISociete): void {
    this.editForm.patchValue({
      id: societe.id,
      nom: societe.nom,
      adresse: societe.adresse,
      telephone: societe.telephone,
      fixe: societe.fixe,
      ninea: societe.ninea,
      logoUrl: societe.logoUrl,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const societe = this.createFromForm();
    if (societe.id !== undefined) {
      this.subscribeToSaveResponse(this.societeService.update(societe));
    } else {
      this.subscribeToSaveResponse(this.societeService.create(societe));
    }
  }

  private createFromForm(): ISociete {
    return {
      ...new Societe(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      fixe: this.editForm.get(['fixe'])!.value,
      ninea: this.editForm.get(['ninea'])!.value,
      logoUrl: this.editForm.get(['logoUrl'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISociete>>): void {
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
}
