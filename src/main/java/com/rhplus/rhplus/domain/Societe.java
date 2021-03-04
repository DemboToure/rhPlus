package com.rhplus.rhplus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Societe.
 */
@Entity
@Table(name = "societe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Societe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @NotNull
    @Column(name = "telephone", nullable = false)
    private String telephone;

    @NotNull
    @Column(name = "fixe", nullable = false)
    private String fixe;

    @Column(name = "ninea")
    private String ninea;

    @Column(name = "logo_url")
    private String logoUrl;

    @OneToMany(mappedBy = "societe")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Employe> employes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Societe nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Societe adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public Societe telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFixe() {
        return fixe;
    }

    public Societe fixe(String fixe) {
        this.fixe = fixe;
        return this;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public String getNinea() {
        return ninea;
    }

    public Societe ninea(String ninea) {
        this.ninea = ninea;
        return this;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public Societe logoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Set<Employe> getEmployes() {
        return employes;
    }

    public Societe employes(Set<Employe> employes) {
        this.employes = employes;
        return this;
    }

    public Societe addEmployes(Employe employe) {
        this.employes.add(employe);
        employe.setSociete(this);
        return this;
    }

    public Societe removeEmployes(Employe employe) {
        this.employes.remove(employe);
        employe.setSociete(null);
        return this;
    }

    public void setEmployes(Set<Employe> employes) {
        this.employes = employes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Societe)) {
            return false;
        }
        return id != null && id.equals(((Societe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Societe{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fixe='" + getFixe() + "'" +
            ", ninea='" + getNinea() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            "}";
    }
}
