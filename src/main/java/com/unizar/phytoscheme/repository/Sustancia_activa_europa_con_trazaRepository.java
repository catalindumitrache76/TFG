package com.unizar.phytoscheme.repository;

import com.unizar.phytoscheme.domain.Sustancia_activa_europa_con_traza;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Sustancia_activa_europa_con_traza entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Sustancia_activa_europa_con_trazaRepository extends JpaRepository<Sustancia_activa_europa_con_traza,Long> {
    
}
