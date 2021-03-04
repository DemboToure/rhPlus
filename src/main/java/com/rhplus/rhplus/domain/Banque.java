package com.rhplus.rhplus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Banque.
 */
@Entity
@Table(name = "banque")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Banque implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "numero_compte", nullable = false)
    private String numeroCompte;

    @NotNull
    @Column(name = "code_banque", nullable = false)
    private String codeBanque;

    @Column(name = "code_guichet")
    private String codeGuichet;

    @Column(name = "cle_rib")
    private String cleRib;

    @OneToMany(mappedBy = "banque")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<BanqueEmploye> compteEmployes = new HashSet<>();

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

    public Banque nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public Banque numeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
        return this;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public Banque codeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
        return this;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getCodeGuichet() {
        return codeGuichet;
    }

    public Banque codeGuichet(String codeGuichet) {
        this.codeGuichet = codeGuichet;
        return this;
    }

    public void setCodeGuichet(String codeGuichet) {
        this.codeGuichet = codeGuichet;
    }

    public String getCleRib() {
        return cleRib;
    }

    public Banque cleRib(String cleRib) {
        this.cleRib = cleRib;
        return this;
    }

    public void setCleRib(String cleRib) {
        this.cleRib = cleRib;
    }

    public Set<BanqueEmploye> getCompteEmployes() {
        return compteEmployes;
    }

    public Banque compteEmployes(Set<BanqueEmploye> banqueEmployes) {
        this.compteEmployes = banqueEmployes;
        return this;
    }

    public Banque addCompteEmployes(BanqueEmploye banqueEmploye) {
        this.compteEmployes.add(banqueEmploye);
        banqueEmploye.setBanque(this);
        return this;
    }

    public Banque removeCompteEmployes(BanqueEmploye banqueEmploye) {
        this.compteEmployes.remove(banqueEmploye);
        banqueEmploye.setBanque(null);
        return this;
    }

    public void setCompteEmployes(Set<BanqueEmploye> banqueEmployes) {
        this.compteEmployes = banqueEmployes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Banque)) {
            return false;
        }
        return id != null && id.equals(((Banque) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Banque{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", numeroCompte='" + getNumeroCompte() + "'" +
            ", codeBanque='" + getCodeBanque() + "'" +
            ", codeGuichet='" + getCodeGuichet() + "'" +
            ", cleRib='" + getCleRib() + "'" +
            "}";
    }
}
