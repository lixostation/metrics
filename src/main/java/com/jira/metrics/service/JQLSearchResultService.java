package com.jira.metrics.service;

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
	
	@Value("${jira.jql.url}")
	private String url;
	@Value("${jira.user.id}")
	private String userId;
	@Value("${jira.user.password}")
	private String userPwd;
	
	

	public JQLSearchResultService() {
		restTemplate = builder.build();
	}
	
	public void login() {
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(userId, userPwd));
	}
	
	public JQLSearchResult runJQL() {
		this.login();
		JQLSearchResult result = restTemplate.getForObject(url, JQLSearchResult.class);
		logger.info(result.toString());
		
		return result;
	}
		
}
