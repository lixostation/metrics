package com.jira.metrics.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jira.metrics.domain.Issue;
import com.jira.metrics.domain.SearchResult;
import com.jira.metrics.repository.IssueRepository;

@Service
public class SearchResultService {

	private RestTemplate restTemplate;
	private RestTemplateBuilder builder = new RestTemplateBuilder();
	private Logger logger = LoggerFactory.getLogger(SearchResultService.class);
	
	@Value("${jira.url}")
	private String url;
	@Value("${jira.user.id}")
	private String userId;
	@Value("${jira.user.password}")
	private String userPwd;
	
	@Autowired
	private IssueRepository issueRepository;
	

	public SearchResultService() {
		restTemplate = builder.build();
	}
	
	public void login() {
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(userId, userPwd));
	}
	
	public SearchResult runJQL() {
		this.login();
		SearchResult result = restTemplate.getForObject(url, SearchResult.class);
		logger.info(result.toString());
		
		return result;
	}
	
	public void saveIssuesToElastic(List<Issue> issueList) {
		issueRepository.saveAll(issueList);
	}
	
}
