import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IContrat, Contrat } from 'app/shared/model/contrat.model';
import { ContratService } from './contrat.service';
import { IEmploye } from 'app/shared/model/employe.model';
import { EmployeService } from 'app/entities/employe/employe.service';

@Component({
  selector: 'jhi-contrat-update',
  templateUrl: './contrat-update.component.html',
})
export class ContratUpdateComponent implements OnInit {
  isSaving = false;
  employes: IEmploye[] = [];
  dateDebutDp: any;
  dateFinDp: any;

  editForm = this.fb.group({
    id: [],
    dateDebut: [],
    dateFin: [],
    salaire: [],
    document: [],
    employe: [],
  });

  constructor(
    protected contratService: ContratService,
    protected employeService: EmployeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contrat }) => {
      this.updateForm(contrat);

      this.employeService.query().subscribe((res: HttpResponse<IEmploye[]>) => (this.employes = res.body || []));
    });
  }

  updateForm(contrat: IContrat): void {
    this.editForm.patchValue({
      id: contrat.id,
      dateDebut: contrat.dateDebut,
      dateFin: contrat.dateFin,
      salaire: contrat.salaire,
      document: contrat.document,
      employe: contrat.employe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contrat = this.createFromForm();
    if (contrat.id !== undefined) {
      this.subscribeToSaveResponse(this.contratService.update(contrat));
    } else {
      this.subscribeToSaveResponse(this.contratService.create(contrat));
    }
  }

  private createFromForm(): IContrat {
    return {
      ...new Contrat(),
      id: this.editForm.get(['id'])!.value,
      dateDebut: this.editForm.get(['dateDebut'])!.value,
      dateFin: this.editForm.get(['dateFin'])!.value,
      salaire: this.editForm.get(['salaire'])!.value,
      document: this.editForm.get(['document'])!.value,
      employe: this.editForm.get(['employe'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContrat>>): void {
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

  trackById(index: number, item: IEmploye): any {
    return item.id;
  }
}
