package com.jira.metrics.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.jira.metrics.domain.Issue;

public interface IssueRepository extends ElasticsearchRepository<Issue, Long>{

}
