package com.rhplus.rhplus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.rhplus.rhplus.domain.enumeration.SituationMatrimoniale;

import com.rhplus.rhplus.domain.enumeration.Sexe;

/**
 * The Employe entity.
 */
@ApiModel(description = "The Employe entity.")
@Entity
@Table(name = "employe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @NotNull
    @Column(name = "lieu_naissance", nullable = false)
    private String lieuNaissance;

    @NotNull
    @Column(name = "cni", nullable = false)
    private String cni;

    @Column(name = "profession")
    private String profession;

    @Column(name = "numero_caisse_securite")
    private String numeroCaisseSecurite;

    @Column(name = "numero_ipres")
    private String numeroIpres;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "prenom_pere")
    private String prenomPere;

    @Column(name = "prenom_mere")
    private String prenomMere;

    @Column(name = "nom_mere")
    private String nomMere;

    @Enumerated(EnumType.STRING)
    @Column(name = "situation_matrimoniale")
    private SituationMatrimoniale situationMatrimoniale;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe")
    private Sexe sexe;

    @Column(name = "trimf")
    private String trimf;

    @Column(name = "statut")
    private String statut;

    @Column(name = "date_embauche")
    private LocalDate dateEmbauche;

    @Column(name = "nationalite")
    private String nationalite;

    @OneToOne
    @JoinColumn(unique = true)
    private BanqueEmploye compteBanquaire;

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Contrat> contrats = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Enfant> enfants = new HashSet<>();

    @OneToMany(mappedBy = "employe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Epoux> epouxs = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "employes", allowSetters = true)
    private Societe societe;

    @ManyToOne
    @JsonIgnoreProperties(value = "employes", allowSetters = true)
    private Poste poste;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public Employe prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public Employe nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public Employe dateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public Employe lieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
        return this;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getCni() {
        return cni;
    }

    public Employe cni(String cni) {
        this.cni = cni;
        return this;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getProfession() {
        return profession;
    }

    public Employe profession(String profession) {
        this.profession = profession;
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getNumeroCaisseSecurite() {
        return numeroCaisseSecurite;
    }

    public Employe numeroCaisseSecurite(String numeroCaisseSecurite) {
        this.numeroCaisseSecurite = numeroCaisseSecurite;
        return this;
    }

    public void setNumeroCaisseSecurite(String numeroCaisseSecurite) {
        this.numeroCaisseSecurite = numeroCaisseSecurite;
    }

    public String getNumeroIpres() {
        return numeroIpres;
    }

    public Employe numeroIpres(String numeroIpres) {
        this.numeroIpres = numeroIpres;
        return this;
    }

    public void setNumeroIpres(String numeroIpres) {
        this.numeroIpres = numeroIpres;
    }

    public String getMatricule() {
        return matricule;
    }

    public Employe matricule(String matricule) {
        this.matricule = matricule;
        return this;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Employe imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrenomPere() {
        return prenomPere;
    }

    public Employe prenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
        return this;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getPrenomMere() {
        return prenomMere;
    }

    public Employe prenomMere(String prenomMere) {
        this.prenomMere = prenomMere;
        return this;
    }

    public void setPrenomMere(String prenomMere) {
        this.prenomMere = prenomMere;
    }

    public String getNomMere() {
        return nomMere;
    }

    public Employe nomMere(String nomMere) {
        this.nomMere = nomMere;
        return this;
    }

    public void setNomMere(String nomMere) {
        this.nomMere = nomMere;
    }

    public SituationMatrimoniale getSituationMatrimoniale() {
        return situationMatrimoniale;
    }

    public Employe situationMatrimoniale(SituationMatrimoniale situationMatrimoniale) {
        this.situationMatrimoniale = situationMatrimoniale;
        return this;
    }

    public void setSituationMatrimoniale(SituationMatrimoniale situationMatrimoniale) {
        this.situationMatrimoniale = situationMatrimoniale;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public Employe sexe(Sexe sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getTrimf() {
        return trimf;
    }

    public Employe trimf(String trimf) {
        this.trimf = trimf;
        return this;
    }

    public void setTrimf(String trimf) {
        this.trimf = trimf;
    }

    public String getStatut() {
        return statut;
    }

    public Employe statut(String statut) {
        this.statut = statut;
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public Employe dateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
        return this;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getNationalite() {
        return nationalite;
    }

    public Employe nationalite(String nationalite) {
        this.nationalite = nationalite;
        return this;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public BanqueEmploye getCompteBanquaire() {
        return compteBanquaire;
    }

    public Employe compteBanquaire(BanqueEmploye banqueEmploye) {
        this.compteBanquaire = banqueEmploye;
        return this;
    }

    public void setCompteBanquaire(BanqueEmploye banqueEmploye) {
        this.compteBanquaire = banqueEmploye;
    }

    public Set<Contrat> getContrats() {
        return contrats;
    }

    public Employe contrats(Set<Contrat> contrats) {
        this.contrats = contrats;
        return this;
    }

    public Employe addContrats(Contrat contrat) {
        this.contrats.add(contrat);
        contrat.setEmploye(this);
        return this;
    }

    public Employe removeContrats(Contrat contrat) {
        this.contrats.remove(contrat);
        contrat.setEmploye(null);
        return this;
    }

    public void setContrats(Set<Contrat> contrats) {
        this.contrats = contrats;
    }

    public Set<Enfant> getEnfants() {
        return enfants;
    }

    public Employe enfants(Set<Enfant> enfants) {
        this.enfants = enfants;
        return this;
    }

    public Employe addEnfants(Enfant enfant) {
        this.enfants.add(enfant);
        enfant.setEmploye(this);
        return this;
    }

    public Employe removeEnfants(Enfant enfant) {
        this.enfants.remove(enfant);
        enfant.setEmploye(null);
        return this;
    }

    public void setEnfants(Set<Enfant> enfants) {
        this.enfants = enfants;
    }

    public Set<Epoux> getEpouxs() {
        return epouxs;
    }

    public Employe epouxs(Set<Epoux> epouxes) {
        this.epouxs = epouxes;
        return this;
    }

    public Employe addEpouxs(Epoux epoux) {
        this.epouxs.add(epoux);
        epoux.setEmploye(this);
        return this;
    }

    public Employe removeEpouxs(Epoux epoux) {
        this.epouxs.remove(epoux);
        epoux.setEmploye(null);
        return this;
    }

    public void setEpouxs(Set<Epoux> epouxes) {
        this.epouxs = epouxes;
    }

    public Societe getSociete() {
        return societe;
    }

    public Employe societe(Societe societe) {
        this.societe = societe;
        return this;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public Poste getPoste() {
        return poste;
    }

    public Employe poste(Poste poste) {
        this.poste = poste;
        return this;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employe)) {
            return false;
        }
        return id != null && id.equals(((Employe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employe{" +
            "id=" + getId() +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", lieuNaissance='" + getLieuNaissance() + "'" +
            ", cni='" + getCni() + "'" +
            ", profession='" + getProfession() + "'" +
            ", numeroCaisseSecurite='" + getNumeroCaisseSecurite() + "'" +
            ", numeroIpres='" + getNumeroIpres() + "'" +
            ", matricule='" + getMatricule() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", prenomPere='" + getPrenomPere() + "'" +
            ", prenomMere='" + getPrenomMere() + "'" +
            ", nomMere='" + getNomMere() + "'" +
            ", situationMatrimoniale='" + getSituationMatrimoniale() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", trimf='" + getTrimf() + "'" +
            ", statut='" + getStatut() + "'" +
            ", dateEmbauche='" + getDateEmbauche() + "'" +
            ", nationalite='" + getNationalite() + "'" +
            "}";
    }
}
