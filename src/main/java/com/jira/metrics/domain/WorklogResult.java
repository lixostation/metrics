package com.jira.metrics.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorklogResult {
	/* ####### Fields ####### */
	private Long maxResults;
	private Long total;
	private List<Worklog> worklogs;
	
	/* ####### Getters and Setters #######*/
	public Long getMaxResults() {
		return maxResults;
	}
	public void setMaxResults(Long maxResults) {
		this.maxResults = maxResults;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<Worklog> getWorklogs() {
		return worklogs;
	}
	public void setWorklogs(List<Worklog> worklogs) {
		this.worklogs = worklogs;
	}
		
	/* ####### toString method ####### */
	@Override
	public String toString() {
		return "WorklogResult [maxResults=" + maxResults + ", total=" + total + ", worklogs=" + worklogs + "]";
	}

}
