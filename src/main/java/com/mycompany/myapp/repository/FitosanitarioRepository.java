package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Fitosanitario;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Fitosanitario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FitosanitarioRepository extends JpaRepository<Fitosanitario,Long> {
    
}
