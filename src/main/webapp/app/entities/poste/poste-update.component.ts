import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPoste, Poste } from 'app/shared/model/poste.model';
import { PosteService } from './poste.service';
import { IServiceEntreprise } from 'app/shared/model/service-entreprise.model';
import { ServiceEntrepriseService } from 'app/entities/service-entreprise/service-entreprise.service';

@Component({
  selector: 'jhi-poste-update',
  templateUrl: './poste-update.component.html',
})
export class PosteUpdateComponent implements OnInit {
  isSaving = false;
  serviceentreprises: IServiceEntreprise[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [],
    nombreRequis: [],
    serviceEntreprise: [],
  });

  constructor(
    protected posteService: PosteService,
    protected serviceEntrepriseService: ServiceEntrepriseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ poste }) => {
      this.updateForm(poste);

      this.serviceEntrepriseService
        .query()
        .subscribe((res: HttpResponse<IServiceEntreprise[]>) => (this.serviceentreprises = res.body || []));
    });
  }

  updateForm(poste: IPoste): void {
    this.editForm.patchValue({
      id: poste.id,
      nom: poste.nom,
      nombreRequis: poste.nombreRequis,
      serviceEntreprise: poste.serviceEntreprise,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const poste = this.createFromForm();
    if (poste.id !== undefined) {
      this.subscribeToSaveResponse(this.posteService.update(poste));
    } else {
      this.subscribeToSaveResponse(this.posteService.create(poste));
    }
  }

  private createFromForm(): IPoste {
    return {
      ...new Poste(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      nombreRequis: this.editForm.get(['nombreRequis'])!.value,
      serviceEntreprise: this.editForm.get(['serviceEntreprise'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPoste>>): void {
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

  trackById(index: number, item: IServiceEntreprise): any {
    return item.id;
  }
}
