package com.mycompany.myapp.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Editorial entity.
 */
public class EditorialDTO implements Serializable {

    private Long id;

    @NotNull
    private String nombreEditorial;

    private Long fitosanitarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEditorial() {
        return nombreEditorial;
    }

    public void setNombreEditorial(String nombreEditorial) {
        this.nombreEditorial = nombreEditorial;
    }

    public Long getFitosanitarioId() {
        return fitosanitarioId;
    }

    public void setFitosanitarioId(Long fitosanitarioId) {
        this.fitosanitarioId = fitosanitarioId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EditorialDTO editorialDTO = (EditorialDTO) o;
        if(editorialDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), editorialDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EditorialDTO{" +
            "id=" + getId() +
            ", nombreEditorial='" + getNombreEditorial() + "'" +
            "}";
    }
}
