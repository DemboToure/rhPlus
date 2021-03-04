package com.rhplus.rhplus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Contrat.
 */
@Entity
@Table(name = "contrat")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "salaire")
    private Float salaire;

    @Column(name = "document")
    private String document;

    @ManyToOne
    @JsonIgnoreProperties(value = "contrats", allowSetters = true)
    private Employe employe;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public Contrat dateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
        return this;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public Contrat dateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
        return this;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Float getSalaire() {
        return salaire;
    }

    public Contrat salaire(Float salaire) {
        this.salaire = salaire;
        return this;
    }

    public void setSalaire(Float salaire) {
        this.salaire = salaire;
    }

    public String getDocument() {
        return document;
    }

    public Contrat document(String document) {
        this.document = document;
        return this;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Contrat employe(Employe employe) {
        this.employe = employe;
        return this;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contrat)) {
            return false;
        }
        return id != null && id.equals(((Contrat) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contrat{" +
            "id=" + getId() +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", salaire=" + getSalaire() +
            ", document='" + getDocument() + "'" +
            "}";
    }
}
