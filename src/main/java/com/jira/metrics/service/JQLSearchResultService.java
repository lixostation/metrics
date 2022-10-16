package com.jira.metrics.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jira.metrics.domain.JQLSearchResult;

@Service
public class JQLSearchResultService {

	private RestTemplate restTemplate;
	private RestTemplateBuilder builder = new RestTemplateBuilder();
	private Logger logger = LoggerFactory.getLogger(JQLSearchResultService.class);
	private Map<String, Integer> startAtParameter = new HashMap<String, Integer>();
	
	@Value("${jira.jql.url}")
	private String url;
	@Value("${jira.user.id}")
	private String userId;
	@Value("${jira.user.password}")
	private String userPwd;
	@Value("${jira.jql.max.per.page}")
	private int maxPerPage;
	
	private int startAt = 0;
	
	public JQLSearchResultService() {
		startAtParameter.put("startAt", startAt);
		restTemplate = builder.build();
	}
	
	public void login() {
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(userId, userPwd));
	}
	
	public JQLSearchResult runJQL() {
		this.login();
		JQLSearchResult result = restTemplate.getForObject(url, JQLSearchResult.class, startAtParameter);
		
		// getting the total of issues to calculate pagination
		int total = 0;
		total = result.getTotal();
		startAt = 1;
		//looping the pagination
		while (startAt < total) {
			startAt = startAt + maxPerPage;
			startAtParameter.put("startAt", startAt);
			result.getIssues().addAll(restTemplate.getForObject(url, JQLSearchResult.class, startAtParameter).getIssues());
			
		}
		logger.info("JQLSearchResult =" + result.toString());
		
		return result;
	}
		
}
