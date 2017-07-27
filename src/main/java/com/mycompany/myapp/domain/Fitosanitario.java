package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Fitosanitario.
 */
@Entity
@Table(name = "fitosanitario")
public class Fitosanitario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "numregistro", nullable = false)
    private String numregistro;

    @Column(name = "nombrecomercial")
    private String nombrecomercial;

    @Column(name = "titular")
    private String titular;

    @Column(name = "formulado")
    private String formulado;

    @OneToMany(mappedBy = "fitosanitario")
    @JsonIgnore
    private Set<Editorial> perteneces = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumregistro() {
        return numregistro;
    }

    public Fitosanitario numregistro(String numregistro) {
        this.numregistro = numregistro;
        return this;
    }

    public void setNumregistro(String numregistro) {
        this.numregistro = numregistro;
    }

    public String getNombrecomercial() {
        return nombrecomercial;
    }

    public Fitosanitario nombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
        return this;
    }

    public void setNombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
    }

    public String getTitular() {
        return titular;
    }

    public Fitosanitario titular(String titular) {
        this.titular = titular;
        return this;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFormulado() {
        return formulado;
    }

    public Fitosanitario formulado(String formulado) {
        this.formulado = formulado;
        return this;
    }

    public void setFormulado(String formulado) {
        this.formulado = formulado;
    }

    public Set<Editorial> getPerteneces() {
        return perteneces;
    }

    public Fitosanitario perteneces(Set<Editorial> editorials) {
        this.perteneces = editorials;
        return this;
    }

    public Fitosanitario addPertenece(Editorial editorial) {
        this.perteneces.add(editorial);
        editorial.setFitosanitario(this);
        return this;
    }

    public Fitosanitario removePertenece(Editorial editorial) {
        this.perteneces.remove(editorial);
        editorial.setFitosanitario(null);
        return this;
    }

    public void setPerteneces(Set<Editorial> editorials) {
        this.perteneces = editorials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fitosanitario fitosanitario = (Fitosanitario) o;
        if (fitosanitario.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fitosanitario.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fitosanitario{" +
            "id=" + getId() +
            ", numregistro='" + getNumregistro() + "'" +
            ", nombrecomercial='" + getNombrecomercial() + "'" +
            ", titular='" + getTitular() + "'" +
            ", formulado='" + getFormulado() + "'" +
            "}";
    }
}
