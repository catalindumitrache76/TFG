package com.unizar.phytoscheme.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Sustancia_activa_europa_con_traza.
 */
@Entity
@Table(name = "sustancia_activa_europa_con_traza")
@Document(indexName = "sustancia_activa_europa_con_traza")
public class Sustancia_activa_europa_con_traza implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "real_id")
    private String real_id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "language")
    private String language;

    @Column(name = "name")
    private String name;

    @Column(name = "source")
    private String source;

    @Column(name = "jhi_date")
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReal_id() {
        return real_id;
    }

    public Sustancia_activa_europa_con_traza real_id(String real_id) {
        this.real_id = real_id;
        return this;
    }

    public void setReal_id(String real_id) {
        this.real_id = real_id;
    }

    public String getTipo() {
        return tipo;
    }

    public Sustancia_activa_europa_con_traza tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLanguage() {
        return language;
    }

    public Sustancia_activa_europa_con_traza language(String language) {
        this.language = language;
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public Sustancia_activa_europa_con_traza name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public Sustancia_activa_europa_con_traza source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDate() {
        return date;
    }

    public Sustancia_activa_europa_con_traza date(String date) {
        this.date = date;
        return this;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sustancia_activa_europa_con_traza sustancia_activa_europa_con_traza = (Sustancia_activa_europa_con_traza) o;
        if (sustancia_activa_europa_con_traza.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sustancia_activa_europa_con_traza.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sustancia_activa_europa_con_traza{" +
            "id=" + getId() +
            ", real_id='" + getReal_id() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", language='" + getLanguage() + "'" +
            ", name='" + getName() + "'" +
            ", source='" + getSource() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
