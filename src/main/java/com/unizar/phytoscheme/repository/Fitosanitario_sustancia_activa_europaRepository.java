package com.unizar.phytoscheme.repository;

import com.unizar.phytoscheme.domain.Fitosanitario_sustancia_activa_europa;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Fitosanitario_sustancia_activa_europa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Fitosanitario_sustancia_activa_europaRepository extends JpaRepository<Fitosanitario_sustancia_activa_europa, Long> {

}
