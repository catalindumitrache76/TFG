package com.unizar.phytoscheme.repository;

import com.unizar.phytoscheme.domain.Fitosanitario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Fitosanitario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FitosanitarioRepository extends JpaRepository<Fitosanitario, Long> {

}
