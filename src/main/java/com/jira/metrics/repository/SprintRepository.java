package com.jira.metrics.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jira.metrics.domain.Sprint;

public interface SprintRepository extends ElasticsearchRepository<Sprint, Long> {
	
}
