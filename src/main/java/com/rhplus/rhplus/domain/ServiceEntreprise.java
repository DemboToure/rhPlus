package com.rhplus.rhplus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ServiceEntreprise.
 */
@Entity
@Table(name = "service_entreprise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ServiceEntreprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "tel")
    private String tel;

    @Column(name = "responsable")
    private Integer responsable;

    @OneToMany(mappedBy = "serviceEntreprise")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Poste> postes = new HashSet<>();

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

    public ServiceEntreprise nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public ServiceEntreprise adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public ServiceEntreprise tel(String tel) {
        this.tel = tel;
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getResponsable() {
        return responsable;
    }

    public ServiceEntreprise responsable(Integer responsable) {
        this.responsable = responsable;
        return this;
    }

    public void setResponsable(Integer responsable) {
        this.responsable = responsable;
    }

    public Set<Poste> getPostes() {
        return postes;
    }

    public ServiceEntreprise postes(Set<Poste> postes) {
        this.postes = postes;
        return this;
    }

    public ServiceEntreprise addPostes(Poste poste) {
        this.postes.add(poste);
        poste.setServiceEntreprise(this);
        return this;
    }

    public ServiceEntreprise removePostes(Poste poste) {
        this.postes.remove(poste);
        poste.setServiceEntreprise(null);
        return this;
    }

    public void setPostes(Set<Poste> postes) {
        this.postes = postes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceEntreprise)) {
            return false;
        }
        return id != null && id.equals(((ServiceEntreprise) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceEntreprise{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", tel='" + getTel() + "'" +
            ", responsable=" + getResponsable() +
            "}";
    }
}
