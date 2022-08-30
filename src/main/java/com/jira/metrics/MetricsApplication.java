package com.jira.metrics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jira.metrics.domain.SearchResult;
import com.jira.metrics.service.SearchResultService;

@SpringBootApplication
public class MetricsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricsApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner run(SearchResultService searchResultService) throws Exception {
		return args ->{
			SearchResult searchResult = searchResultService.runJQL();
			searchResultService.saveIssuesToElastic(searchResult.getIssues());
			
			System.exit(0);
		};
		
	}

}
