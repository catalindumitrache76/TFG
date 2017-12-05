package com.unizar.phytoscheme.repository;

import com.unizar.phytoscheme.domain.Sustancia_activa_europa;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Sustancia_activa_europa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface Sustancia_activa_europaRepository extends JpaRepository<Sustancia_activa_europa, Long> {

}
