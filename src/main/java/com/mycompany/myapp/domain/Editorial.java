package com.mycompany.myapp.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Editorial.
 */
@Entity
@Table(name = "editorial")
public class Editorial implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre_editorial", nullable = false)
    private String nombreEditorial;

    @ManyToOne
    private Fitosanitario fitosanitario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public Editorial nombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
        return this;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public Fitosanitario getFitosanitario() {
        return fitosanitario;
    }

    public Editorial fitosanitario(Fitosanitario fitosanitario) {
        this.fitosanitario = fitosanitario;
        return this;
    }

    public void setFitosanitario(Fitosanitario fitosanitario) {
        this.fitosanitario = fitosanitario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Editorial editorial = (Editorial) o;
        if (editorial.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), editorial.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Editorial{" +
            "id=" + getId() +
            ", nombreEditorial='" + getNombreEditorial() + "'" +
            "}";
    }
}
