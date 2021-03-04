package com.rhplus.rhplus.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BanqueEmploye.
 */
@Entity
@Table(name = "banque_employe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BanqueEmploye implements Serializable {

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

    @Column(name = "pdf_domiciliation")
    private String pdfDomiciliation;

    @OneToOne(mappedBy = "compteBanquaire")
    @JsonIgnore
    private Employe employe;

    @ManyToOne
    @JsonIgnoreProperties(value = "compteEmployes", allowSetters = true)
    private Banque banque;

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

    public BanqueEmploye nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public BanqueEmploye numeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
        return this;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public String getCodeBanque() {
        return codeBanque;
    }

    public BanqueEmploye codeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
        return this;
    }

    public void setCodeBanque(String codeBanque) {
        this.codeBanque = codeBanque;
    }

    public String getCodeGuichet() {
        return codeGuichet;
    }

    public BanqueEmploye codeGuichet(String codeGuichet) {
        this.codeGuichet = codeGuichet;
        return this;
    }

    public void setCodeGuichet(String codeGuichet) {
        this.codeGuichet = codeGuichet;
    }

    public String getCleRib() {
        return cleRib;
    }

    public BanqueEmploye cleRib(String cleRib) {
        this.cleRib = cleRib;
        return this;
    }

    public void setCleRib(String cleRib) {
        this.cleRib = cleRib;
    }

    public String getPdfDomiciliation() {
        return pdfDomiciliation;
    }

    public BanqueEmploye pdfDomiciliation(String pdfDomiciliation) {
        this.pdfDomiciliation = pdfDomiciliation;
        return this;
    }

    public void setPdfDomiciliation(String pdfDomiciliation) {
        this.pdfDomiciliation = pdfDomiciliation;
    }

    public Employe getEmploye() {
        return employe;
    }

    public BanqueEmploye employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Banque getBanque() {
        return banque;
    }

    public BanqueEmploye banque(Banque banque) {
        this.banque = banque;
        return this;
    }

    public void setBanque(Banque banque) {
        this.banque = banque;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BanqueEmploye)) {
            return false;
        }
        return id != null && id.equals(((BanqueEmploye) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BanqueEmploye{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", numeroCompte='" + getNumeroCompte() + "'" +
            ", codeBanque='" + getCodeBanque() + "'" +
            ", codeGuichet='" + getCodeGuichet() + "'" +
            ", cleRib='" + getCleRib() + "'" +
            ", pdfDomiciliation='" + getPdfDomiciliation() + "'" +
            "}";
    }
}
