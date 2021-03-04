import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEpoux, Epoux } from 'app/shared/model/epoux.model';
import { EpouxService } from './epoux.service';
import { IEmploye } from 'app/shared/model/employe.model';
import { EmployeService } from 'app/entities/employe/employe.service';

@Component({
  selector: 'jhi-epoux-update',
  templateUrl: './epoux-update.component.html',
})
export class EpouxUpdateComponent implements OnInit {
  isSaving = false;
  employes: IEmploye[] = [];
  dateNaissanceDp: any;

  editForm = this.fb.group({
    id: [],
    prenom: [null, [Validators.required]],
    nom: [null, [Validators.required]],
    dateNaissance: [null, [Validators.required]],
    lieuNaissance: [null, [Validators.required]],
    employe: [],
  });

  constructor(
    protected epouxService: EpouxService,
    protected employeService: EmployeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ epoux }) => {
      this.updateForm(epoux);

      this.employeService.query().subscribe((res: HttpResponse<IEmploye[]>) => (this.employes = res.body || []));
    });
  }

  updateForm(epoux: IEpoux): void {
    this.editForm.patchValue({
      id: epoux.id,
      prenom: epoux.prenom,
      nom: epoux.nom,
      dateNaissance: epoux.dateNaissance,
      lieuNaissance: epoux.lieuNaissance,
      employe: epoux.employe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const epoux = this.createFromForm();
    if (epoux.id !== undefined) {
      this.subscribeToSaveResponse(this.epouxService.update(epoux));
    } else {
      this.subscribeToSaveResponse(this.epouxService.create(epoux));
    }
  }

  private createFromForm(): IEpoux {
    return {
      ...new Epoux(),
      id: this.editForm.get(['id'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      dateNaissance: this.editForm.get(['dateNaissance'])!.value,
      lieuNaissance: this.editForm.get(['lieuNaissance'])!.value,
      employe: this.editForm.get(['employe'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEpoux>>): void {
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
