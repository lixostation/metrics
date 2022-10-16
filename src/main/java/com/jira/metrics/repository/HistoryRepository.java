package com.jira.metrics.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.jira.metrics.domain.History;

public interface HistoryRepository extends ElasticsearchRepository<History, Long> {

}
