package com.jira.metrics;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jira.metrics.domain.JQLSearchResult;
import com.jira.metrics.domain.Worklog;
import com.jira.metrics.service.IssueService;
import com.jira.metrics.service.JQLSearchResultService;
import com.jira.metrics.service.SprintService;
import com.jira.metrics.service.WorklogService;

@SpringBootApplication
public class MetricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricsApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner run(JQLSearchResultService searchResultService, 
			                     IssueService issueService,
			                     WorklogService worklogService,
			                     SprintService sprintService) throws Exception {
		return args ->{
			JQLSearchResult jqlSearchResult = searchResultService.runJQL();
			
			issueService.saveIssuesToElastic(jqlSearchResult.getIssues());
			List<String> issueKeyList = issueService.getIssuekeysFromElastic();
			
			List<Worklog> worklogList = worklogService.getWorklogsFromJira(issueKeyList);
			worklogService.saveWorklogsToElastic(worklogList);
			
			sprintService.saveSprintsToElastic();
			
			System.exit(0);
		};
		
	}

}
