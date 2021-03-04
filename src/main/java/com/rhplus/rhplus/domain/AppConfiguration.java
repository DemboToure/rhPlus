package com.rhplus.rhplus.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A AppConfiguration.
 */
@Entity
@Table(name = "app_configuration")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nom_entreprise", nullable = false)
    private String nomEntreprise;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomEntreprise() {
        return nomEntreprise;
    }

    public AppConfiguration nomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
        return this;
    }

    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

    public String getAdresse() {
        return adresse;
    }

    public AppConfiguration adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public AppConfiguration telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFixe() {
        return fixe;
    }

    public AppConfiguration fixe(String fixe) {
        this.fixe = fixe;
        return this;
    }

    public void setFixe(String fixe) {
        this.fixe = fixe;
    }

    public String getNinea() {
        return ninea;
    }

    public AppConfiguration ninea(String ninea) {
        this.ninea = ninea;
        return this;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public AppConfiguration logoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppConfiguration)) {
            return false;
        }
        return id != null && id.equals(((AppConfiguration) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppConfiguration{" +
            "id=" + getId() +
            ", nomEntreprise='" + getNomEntreprise() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fixe='" + getFixe() + "'" +
            ", ninea='" + getNinea() + "'" +
            ", logoUrl='" + getLogoUrl() + "'" +
            "}";
    }
}
