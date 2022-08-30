package com.jira.metrics.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jira.metrics.domain.Worklog;
import com.jira.metrics.domain.WorklogResult;
import com.jira.metrics.repository.WorklogRepository;

@Service
public class WorklogService {
	@Autowired
	private WorklogRepository worklogRepository;
	
	private RestTemplate restTemplate;
	private RestTemplateBuilder builder = new RestTemplateBuilder();
	private Logger logger = LoggerFactory.getLogger(JQLSearchResultService.class);
	
	@Value("${jira.issue.url}")
	private String url;
	@Value("${jira.user.id}")
	private String userId;
	@Value("${jira.user.password}")
	private String userPwd;
	
	

	public WorklogService() {
		restTemplate = builder.build();
	}
	
	public void login() {
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(userId, userPwd));
	}
	/**
	 * Method to collect worklogs from JIRA based on a list of issue keys.
	 * Issue Key needs to be passed to JIRA API in order to collect the worklogs for that issue.
	 * @param issueKeysList
	 * @return
	 */
	public List<Worklog> getWorklogsFromJira(List<String> issueKeysList) {
		this.login();
		List<Worklog> result = new ArrayList<Worklog>();
		for (String key : issueKeysList) {
			logger.info("Collecting worklogs for: " + key);
			String issueUrl = url + key + "/worklog";
			logger.info(issueUrl);
			WorklogResult worklogResult = restTemplate.getForObject(issueUrl, WorklogResult.class);
			result.addAll(worklogResult.getWorklogs());
		}
		
		return result;
	}
	/**
	 * Method to save the Worklogs on elastic search
	 * @param worklogList
	 */
	public void saveWorklogsToElastic(List<Worklog> worklogList) {
		worklogRepository.saveAll(worklogList);
	}
	
}
