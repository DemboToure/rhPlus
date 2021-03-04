package com.rhplus.rhplus.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Poste.
 */
@Entity
@Table(name = "poste")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Poste implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "nombre_requis")
    private Integer nombreRequis;

    @OneToMany(mappedBy = "poste")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Employe> employes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "postes", allowSetters = true)
    private ServiceEntreprise serviceEntreprise;

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

    public Poste nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNombreRequis() {
        return nombreRequis;
    }

    public Poste nombreRequis(Integer nombreRequis) {
        this.nombreRequis = nombreRequis;
        return this;
    }

    public void setNombreRequis(Integer nombreRequis) {
        this.nombreRequis = nombreRequis;
    }

    public Set<Employe> getEmployes() {
        return employes;
    }

    public Poste employes(Set<Employe> employes) {
        this.employes = employes;
        return this;
    }

    public Poste addEmployes(Employe employe) {
        this.employes.add(employe);
        employe.setPoste(this);
        return this;
    }

    public Poste removeEmployes(Employe employe) {
        this.employes.remove(employe);
        employe.setPoste(null);
        return this;
    }

    public void setEmployes(Set<Employe> employes) {
        this.employes = employes;
    }

    public ServiceEntreprise getServiceEntreprise() {
        return serviceEntreprise;
    }

    public Poste serviceEntreprise(ServiceEntreprise serviceEntreprise) {
        this.serviceEntreprise = serviceEntreprise;
        return this;
    }

    public void setServiceEntreprise(ServiceEntreprise serviceEntreprise) {
        this.serviceEntreprise = serviceEntreprise;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Poste)) {
            return false;
        }
        return id != null && id.equals(((Poste) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Poste{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", nombreRequis=" + getNombreRequis() +
            "}";
    }
}
