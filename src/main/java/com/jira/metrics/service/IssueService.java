package com.jira.metrics.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.metrics.domain.Issue;
import com.jira.metrics.repository.IssueRepository;

@Service
public class IssueService {
	@Autowired
	private IssueRepository issueRepository;
	
	/**
	 * Method to save a list of Issues to the elastic search
	 * @param issueList
	 */
	public void saveIssuesToElastic(List<Issue> issueList) {
		issueRepository.saveAll(issueList);
	}
	
	/**
	 * Method to get all the issues from elastic search, collect just the key for each issue and add on a list to be returned
	 * @return List of string of all the keys from issues on elastic search
	 */
	public List<String> getIssuekeysFromElastic(){
		List<String> result = new ArrayList<String>();
		Iterable<Issue> issueIterable = issueRepository.findAll();
		for (Iterator<Issue> iterator = issueIterable.iterator(); iterator.hasNext();) {
			Issue issue = iterator.next();
			result.add(issue.getKey());
		}
		
		return result;
	}

	
}
