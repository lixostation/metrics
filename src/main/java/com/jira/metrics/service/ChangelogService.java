package com.jira.metrics.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jira.metrics.domain.Changelog;
import com.jira.metrics.domain.History;

@Service
public class ChangelogService {

	private RestTemplate restTemplate;
	private RestTemplateBuilder builder = new RestTemplateBuilder();
	private Logger logger = LoggerFactory.getLogger(ChangelogService.class);
	
	@Value("${jira.issue.url}")
	private String url;
	@Value("${jira.user.id}")
	private String userId;
	@Value("${jira.user.password}")
	private String userPwd;
	
	public ChangelogService() {
		restTemplate = builder.build();
	}

	public void login() {
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(userId, userPwd));
	}
	
	public List<History> getHistoryFromJira(List<String> issueKeysList){
		this.login();
		List<History> result = new ArrayList<History>();
		for (String key : issueKeysList) {
			logger.info("Collecting history for: " + key);
			String issueUrl = url + key + "?expand=changelog";
			Changelog changelogResult = restTemplate.getForObject(issueUrl, Changelog.class);
			result.addAll(changelogResult.getHistories());
		}
		return result;
	}
}
