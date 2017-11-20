package com.unizar.phytoscheme.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Fitosanitario_sustancia_activa_europa.
 */
@Entity
@Table(name = "fitosanitario_sustancia_activa_europa")
@Document(indexName = "fitosanitario_sustancia_activa_europa")
public class Fitosanitario_sustancia_activa_europa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numregistro")
    private String numregistro;

    @Column(name = "nombrecomercial")
    private String nombrecomercial;

    @Column(name = "titular")
    private String titular;

    @Column(name = "formulado")
    private String formulado;

    @Column(name = "active_substance_id")
    private String activeSubstanceID;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumregistro() {
        return numregistro;
    }

    public Fitosanitario_sustancia_activa_europa numregistro(String numregistro) {
        this.numregistro = numregistro;
        return this;
    }

    public void setNumregistro(String numregistro) {
        this.numregistro = numregistro;
    }

    public String getNombrecomercial() {
        return nombrecomercial;
    }

    public Fitosanitario_sustancia_activa_europa nombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
        return this;
    }

    public void setNombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
    }

    public String getTitular() {
        return titular;
    }

    public Fitosanitario_sustancia_activa_europa titular(String titular) {
        this.titular = titular;
        return this;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFormulado() {
        return formulado;
    }

    public Fitosanitario_sustancia_activa_europa formulado(String formulado) {
        this.formulado = formulado;
        return this;
    }

    public void setFormulado(String formulado) {
        this.formulado = formulado;
    }

    public String getActiveSubstanceID() {
        return activeSubstanceID;
    }

    public Fitosanitario_sustancia_activa_europa activeSubstanceID(String activeSubstanceID) {
        this.activeSubstanceID = activeSubstanceID;
        return this;
    }

    public void setActiveSubstanceID(String activeSubstanceID) {
        this.activeSubstanceID = activeSubstanceID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Fitosanitario_sustancia_activa_europa fitosanitario_sustancia_activa_europa = (Fitosanitario_sustancia_activa_europa) o;
        if (fitosanitario_sustancia_activa_europa.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fitosanitario_sustancia_activa_europa.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Fitosanitario_sustancia_activa_europa{" +
            "id=" + getId() +
            ", numregistro='" + getNumregistro() + "'" +
            ", nombrecomercial='" + getNombrecomercial() + "'" +
            ", titular='" + getTitular() + "'" +
            ", formulado='" + getFormulado() + "'" +
            ", activeSubstanceID='" + getActiveSubstanceID() + "'" +
            "}";
    }
}
