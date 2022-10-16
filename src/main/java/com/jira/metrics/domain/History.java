package com.jira.metrics.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "histories")
public class History {
	/* ####### Fields ####### */
	@Id
	private Long changelogId;
	@Field(type = FieldType.Long, name = "issueId")
	private Long issueId;
	@Field(type = FieldType.Text, name = "issueKey")
	private String issueKey;
	@Field(type = FieldType.Date, name = "created")
	private Date created;
	@Field(type = FieldType.Text, name = "statusFrom")
	private String statusFrom;
	@Field(type = FieldType.Text, name = "statusTo")
	private String statusTo;
	@Field(type = FieldType.Double, name = "StoryPointsFrom")
	private double StoryPointsFrom;
	@Field(type = FieldType.Double, name = "StoryPointsTo")
	private double StoryPointsTo; 
	@Field(type = FieldType.Long, name = "timeOriginalEstimateFrom")
	private Long timeOriginalEstimateFrom;
	@Field(type = FieldType.Long, name = "timeOriginalEstimateTo")
	private Long timeOriginalEstimateTo;
	@Field(type = FieldType.Long, name = "timeEstimateFrom")
	private Long timeEstimateFrom;
	@Field(type = FieldType.Long, name = "timeEstimateTo")
	private Long timeEstimateTo;
	@Field(type = FieldType.Long, name = "timeSpentFrom")
	private Long timeSpentFrom;
	@Field(type = FieldType.Long, name = "timeSpentTo")
	private Long timeSpentTo;

	/* ####### Getter and Setters ####### */
	public Long getChangelogId() {
		return changelogId;
	}
	public void setChangelogId(Long changelogId) {
		this.changelogId = changelogId;
	}
	public Long getIssueId() {
		return issueId;
	}
	public void setIssueId(Long issueId) {
		this.issueId = issueId;
	}
	public String getIssueKey() {
		return issueKey;
	}
	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getStatusFrom() {
		return statusFrom;
	}
	public void setStatusFrom(String statusFrom) {
		this.statusFrom = statusFrom;
	}
	public String getStatusTo() {
		return statusTo;
	}
	public void setStatusTo(String statusTo) {
		this.statusTo = statusTo;
	}
	public double getStoryPointsFrom() {
		return StoryPointsFrom;
	}
	public void setStoryPointsFrom(double storyPointsFrom) {
		StoryPointsFrom = storyPointsFrom;
	}
	public double getStoryPointsTo() {
		return StoryPointsTo;
	}
	public void setStoryPointsTo(double storyPointsTo) {
		StoryPointsTo = storyPointsTo;
	}
	public Long getTimeOriginalEstimateFrom() {
		return timeOriginalEstimateFrom;
	}
	public void setTimeOriginalEstimateFrom(Long timeOriginalEstimateFrom) {
		this.timeOriginalEstimateFrom = timeOriginalEstimateFrom;
	}
	public Long getTimeOriginalEstimateTo() {
		return timeOriginalEstimateTo;
	}
	public void setTimeOriginalEstimateTo(Long timeOriginalEstimateTo) {
		this.timeOriginalEstimateTo = timeOriginalEstimateTo;
	}
	public Long getTimeEstimateFrom() {
		return timeEstimateFrom;
	}
	public void setTimeEstimateFrom(Long timeEstimateFrom) {
		this.timeEstimateFrom = timeEstimateFrom;
	}
	public Long getTimeEstimateTo() {
		return timeEstimateTo;
	}
	public void setTimeEstimateTo(Long timeEstimateTo) {
		this.timeEstimateTo = timeEstimateTo;
	}
	public Long getTimeSpentFrom() {
		return timeSpentFrom;
	}
	public void setTimeSpentFrom(Long timeSpentFrom) {
		this.timeSpentFrom = timeSpentFrom;
	}
	public Long getTimeSpentTo() {
		return timeSpentTo;
	}
	public void setTimeSpentTo(Long timeSpentTo) {
		this.timeSpentTo = timeSpentTo;
	}
	
	/* ####### toString method ####### */
	@Override
	public String toString() {
		return "History [changelogId=" + changelogId + ", issueId=" + issueId + ", issueKey=" + issueKey + ", created="
				+ created + ", statusFrom=" + statusFrom + ", statusTo=" + statusTo + ", StoryPointsFrom="
				+ StoryPointsFrom + ", StoryPointsTo=" + StoryPointsTo + ", timeOriginalEstimateFrom="
				+ timeOriginalEstimateFrom + ", timeOriginalEstimateTo=" + timeOriginalEstimateTo
				+ ", timeEstimateFrom=" + timeEstimateFrom + ", timeEstimateTo=" + timeEstimateTo + ", timeSpentFrom="
				+ timeSpentFrom + ", timeSpentTo=" + timeSpentTo + "]";
	}
			
}
