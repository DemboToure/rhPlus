import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IServiceEntreprise, ServiceEntreprise } from 'app/shared/model/service-entreprise.model';
import { ServiceEntrepriseService } from './service-entreprise.service';

@Component({
  selector: 'jhi-service-entreprise-update',
  templateUrl: './service-entreprise-update.component.html',
})
export class ServiceEntrepriseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    adresse: [],
    tel: [],
    responsable: [],
  });

  constructor(
    protected serviceEntrepriseService: ServiceEntrepriseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ serviceEntreprise }) => {
      this.updateForm(serviceEntreprise);
    });
  }

  updateForm(serviceEntreprise: IServiceEntreprise): void {
    this.editForm.patchValue({
      id: serviceEntreprise.id,
      nom: serviceEntreprise.nom,
      adresse: serviceEntreprise.adresse,
      tel: serviceEntreprise.tel,
      responsable: serviceEntreprise.responsable,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const serviceEntreprise = this.createFromForm();
    if (serviceEntreprise.id !== undefined) {
      this.subscribeToSaveResponse(this.serviceEntrepriseService.update(serviceEntreprise));
    } else {
      this.subscribeToSaveResponse(this.serviceEntrepriseService.create(serviceEntreprise));
    }
  }

  private createFromForm(): IServiceEntreprise {
    return {
      ...new ServiceEntreprise(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
      tel: this.editForm.get(['tel'])!.value,
      responsable: this.editForm.get(['responsable'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServiceEntreprise>>): void {
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
