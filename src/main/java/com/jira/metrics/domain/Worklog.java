package com.jira.metrics.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "worklog")
public class Worklog {
	/* ####### Fields ####### */
	@Id
	private Long id;
	
	@Field(type = FieldType.Long, name = "issueId")
	private Long issueId;
	
	@Field(type = FieldType.Date, name = "started")
	private Date started;
	
	@Field(type = FieldType.Long, name = "timeSpentSeconds")
	private Long timeSpentSeconds;
	
	@Field(type = FieldType.Date, name = "created")
	private Date created;
	
	@Field(type = FieldType.Date, name = "upDated")
	private Date updated;
	
	/* ####### Getters and Setters #######*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIssueId() {
		return issueId;
	}
	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}
	public Date getStarted() {
		return started;
	}
	public void setStarted(Date started) {
		this.started = started;
	}
	public Long getTimeSpentSeconds() {
		return timeSpentSeconds;
	}
	public void setTimeSpentSeconds(Long timeSpentSeconds) {
		this.timeSpentSeconds = timeSpentSeconds;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	/* ####### toString method ####### */
	@Override
	public String toString() {
		return "Worklog [id=" + id + ", issueId=" + issueId + ", started=" + started + ", timeSpentSeconds="
				+ timeSpentSeconds + ", created=" + created + ", updated=" + updated + "]";
	}

}
