package com.unizar.phytoscheme.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A Fitosanitario.
 */
@Entity
@Table(name = "fitosanitario")
@Document(indexName = "fitosanitario")
public class Fitosanitario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_registro")
    private String num_registro;

    @Column(name = "nombre_comercial")
    private String nombre_comercial;

    @Column(name = "titular")
    private String titular;

    @Column(name = "formulado")
    private String formulado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum_registro() {
        return num_registro;
    }

    public Fitosanitario num_registro(String num_registro) {
        this.num_registro = num_registro;
        return this;
    }

    public void setNum_registro(String num_registro) {
        this.num_registro = num_registro;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public Fitosanitario nombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
        return this;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
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
            ", num_registro='" + getNum_registro() + "'" +
            ", nombre_comercial='" + getNombre_comercial() + "'" +
            ", titular='" + getTitular() + "'" +
            ", formulado='" + getFormulado() + "'" +
            "}";
    }
}
