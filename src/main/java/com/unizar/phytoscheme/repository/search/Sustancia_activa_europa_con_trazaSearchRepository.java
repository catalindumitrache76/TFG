package com.unizar.phytoscheme.repository.search;

import com.unizar.phytoscheme.domain.Sustancia_activa_europa_con_traza;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Sustancia_activa_europa_con_traza entity.
 */
public interface Sustancia_activa_europa_con_trazaSearchRepository extends ElasticsearchRepository<Sustancia_activa_europa_con_traza, Long> {
}
