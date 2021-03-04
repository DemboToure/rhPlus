import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEmploye, Employe } from 'app/shared/model/employe.model';
import { EmployeService } from './employe.service';
import { IBanqueEmploye } from 'app/shared/model/banque-employe.model';
import { BanqueEmployeService } from 'app/entities/banque-employe/banque-employe.service';
import { ISociete } from 'app/shared/model/societe.model';
import { SocieteService } from 'app/entities/societe/societe.service';
import { IPoste } from 'app/shared/model/poste.model';
import { PosteService } from 'app/entities/poste/poste.service';

type SelectableEntity = IBanqueEmploye | ISociete | IPoste;

@Component({
  selector: 'jhi-employe-update',
  templateUrl: './employe-update.component.html',
})
export class EmployeUpdateComponent implements OnInit {
  isSaving = false;
  comptebanquaires: IBanqueEmploye[] = [];
  societes: ISociete[] = [];
  postes: IPoste[] = [];
  dateNaissanceDp: any;
  dateEmbaucheDp: any;

  editForm = this.fb.group({
    id: [],
    prenom: [null, [Validators.required]],
    nom: [null, [Validators.required]],
    dateNaissance: [null, [Validators.required]],
    lieuNaissance: [null, [Validators.required]],
    cni: [null, [Validators.required]],
    profession: [],
    numeroCaisseSecurite: [],
    numeroIpres: [],
    matricule: [],
    imageUrl: [],
    prenomPere: [],
    prenomMere: [],
    nomMere: [],
    situationMatrimoniale: [],
    sexe: [],
    trimf: [],
    statut: [],
    dateEmbauche: [],
    nationalite: [],
    compteBanquaire: [],
    societe: [],
    poste: [],
  });

  constructor(
    protected employeService: EmployeService,
    protected banqueEmployeService: BanqueEmployeService,
    protected societeService: SocieteService,
    protected posteService: PosteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ employe }) => {
      this.updateForm(employe);

      this.banqueEmployeService
        .query({ filter: 'employe-is-null' })
        .pipe(
          map((res: HttpResponse<IBanqueEmploye[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IBanqueEmploye[]) => {
          if (!employe.compteBanquaire || !employe.compteBanquaire.id) {
            this.comptebanquaires = resBody;
          } else {
            this.banqueEmployeService
              .find(employe.compteBanquaire.id)
              .pipe(
                map((subRes: HttpResponse<IBanqueEmploye>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IBanqueEmploye[]) => (this.comptebanquaires = concatRes));
          }
        });

      this.societeService.query().subscribe((res: HttpResponse<ISociete[]>) => (this.societes = res.body || []));

      this.posteService.query().subscribe((res: HttpResponse<IPoste[]>) => (this.postes = res.body || []));
    });
  }

  updateForm(employe: IEmploye): void {
    this.editForm.patchValue({
      id: employe.id,
      prenom: employe.prenom,
      nom: employe.nom,
      dateNaissance: employe.dateNaissance,
      lieuNaissance: employe.lieuNaissance,
      cni: employe.cni,
      profession: employe.profession,
      numeroCaisseSecurite: employe.numeroCaisseSecurite,
      numeroIpres: employe.numeroIpres,
      matricule: employe.matricule,
      imageUrl: employe.imageUrl,
      prenomPere: employe.prenomPere,
      prenomMere: employe.prenomMere,
      nomMere: employe.nomMere,
      situationMatrimoniale: employe.situationMatrimoniale,
      sexe: employe.sexe,
      trimf: employe.trimf,
      statut: employe.statut,
      dateEmbauche: employe.dateEmbauche,
      nationalite: employe.nationalite,
      compteBanquaire: employe.compteBanquaire,
      societe: employe.societe,
      poste: employe.poste,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const employe = this.createFromForm();
    if (employe.id !== undefined) {
      this.subscribeToSaveResponse(this.employeService.update(employe));
    } else {
      this.subscribeToSaveResponse(this.employeService.create(employe));
    }
  }

  private createFromForm(): IEmploye {
    return {
      ...new Employe(),
      id: this.editForm.get(['id'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      dateNaissance: this.editForm.get(['dateNaissance'])!.value,
      lieuNaissance: this.editForm.get(['lieuNaissance'])!.value,
      cni: this.editForm.get(['cni'])!.value,
      profession: this.editForm.get(['profession'])!.value,
      numeroCaisseSecurite: this.editForm.get(['numeroCaisseSecurite'])!.value,
      numeroIpres: this.editForm.get(['numeroIpres'])!.value,
      matricule: this.editForm.get(['matricule'])!.value,
      imageUrl: this.editForm.get(['imageUrl'])!.value,
      prenomPere: this.editForm.get(['prenomPere'])!.value,
      prenomMere: this.editForm.get(['prenomMere'])!.value,
      nomMere: this.editForm.get(['nomMere'])!.value,
      situationMatrimoniale: this.editForm.get(['situationMatrimoniale'])!.value,
      sexe: this.editForm.get(['sexe'])!.value,
      trimf: this.editForm.get(['trimf'])!.value,
      statut: this.editForm.get(['statut'])!.value,
      dateEmbauche: this.editForm.get(['dateEmbauche'])!.value,
      nationalite: this.editForm.get(['nationalite'])!.value,
      compteBanquaire: this.editForm.get(['compteBanquaire'])!.value,
      societe: this.editForm.get(['societe'])!.value,
      poste: this.editForm.get(['poste'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmploye>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
