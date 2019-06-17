package com.tothapplication.repository.search;

import com.tothapplication.domain.CCP;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link CCP} entity.
 */
public interface CCPSearchRepository extends ElasticsearchRepository<CCP, Long> {
}
