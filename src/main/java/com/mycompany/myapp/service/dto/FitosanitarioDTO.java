package com.mycompany.myapp.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Fitosanitario entity.
 */
public class FitosanitarioDTO implements Serializable {

    private Long id;

    @NotNull
    private String numregistro;

    private String nombrecomercial;

    private String titular;

    private String formulado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumregistro() {
        return numregistro;
    }

    public void setNumregistro(String numregistro) {
        this.numregistro = numregistro;
    }

    public String getNombrecomercial() {
        return nombrecomercial;
    }

    public void setNombrecomercial(String nombrecomercial) {
        this.nombrecomercial = nombrecomercial;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFormulado() {
        return formulado;
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

        FitosanitarioDTO fitosanitarioDTO = (FitosanitarioDTO) o;
        if(fitosanitarioDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), fitosanitarioDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FitosanitarioDTO{" +
            "id=" + getId() +
            ", numregistro='" + getNumregistro() + "'" +
            ", nombrecomercial='" + getNombrecomercial() + "'" +
            ", titular='" + getTitular() + "'" +
            ", formulado='" + getFormulado() + "'" +
            "}";
    }
}
