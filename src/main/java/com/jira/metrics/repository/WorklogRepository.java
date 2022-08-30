package com.jira.metrics.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jira.metrics.domain.Worklog;

public interface WorklogRepository extends ElasticsearchRepository<Worklog, Long> {

}
