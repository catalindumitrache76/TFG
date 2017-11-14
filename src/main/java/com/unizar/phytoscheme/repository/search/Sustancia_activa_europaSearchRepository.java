package com.unizar.phytoscheme.repository.search;

import com.unizar.phytoscheme.domain.Sustancia_activa_europa;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Sustancia_activa_europa entity.
 */
public interface Sustancia_activa_europaSearchRepository extends ElasticsearchRepository<Sustancia_activa_europa, Long> {
}
