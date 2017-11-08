package com.unizar.phytoscheme.repository.search;

import com.unizar.phytoscheme.domain.Fitosanitario;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Fitosanitario entity.
 */
public interface FitosanitarioSearchRepository extends ElasticsearchRepository<Fitosanitario, Long> {
}
