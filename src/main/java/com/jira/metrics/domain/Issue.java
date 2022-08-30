package com.jira.metrics.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "issues")
public class Issue {
	/* ####### Fields ####### */
	@Id
	private Long id;
	
	@Field(type = FieldType.Text, name = "key")
	private String key;
	
	@Field(type = FieldType.Text, name = "summary")
	private String summary;
	
	@Field(type = FieldType.Text, name = "issuetypeName")
	private String issuetypeName;
	
	@Field(type = FieldType.Text, name = "status")
	private String status;
	
	@Field(type = FieldType.Double, name = "storyPoints")
	private Double storyPoints;
	
	@Field(type = FieldType.Long, name = "sprintId")
	private Long sprintId;
	
	@Field(type = FieldType.Text, name = "sprintName")
	private String sprintName;
	
	@Field(type = FieldType.Long, name = "timeOriginalEstimate")
	private Long timeOriginalEstimate;
	
	@Field(type = FieldType.Long, name = "timeEstimate")
	private Long timeEstimate;
	
	@Field(type = FieldType.Long, name = "timeSpent")
	private Long timeSpent;
	
	@Field(type = FieldType.Text, name = "rootCause")
	private String rootCause;
	
	@Field(type = FieldType.Text, name = "rootCauseAnalysis")
	private String rootCauseAnalysis;
	
	@Field(type = FieldType.Long, name = "projectId")
	private Long projectId;
	
	@Field(type = FieldType.Text, name = "projectKey")
	private String projectKey;
	
	@Field(type = FieldType.Text, name = "projectName")
	private String projectName;
	
	/* ####### Getters and Setters #######*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getIssuetypeName() {
		return issuetypeName;
	}

	public void setIssuetypeName(String issuetypeName) {
		this.issuetypeName = issuetypeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(Double storyPoints) {
		this.storyPoints = storyPoints;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public String getSprintName() {
		return sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	public Long getTimeOriginalEstimate() {
		return timeOriginalEstimate;
	}

	public void setTimeOriginalEstimate(Long timeOriginalEstimate) {
		this.timeOriginalEstimate = timeOriginalEstimate;
	}

	public Long getTimeEstimate() {
		return timeEstimate;
	}

	public void setTimeEstimate(Long timeEstimate) {
		this.timeEstimate = timeEstimate;
	}

	public Long getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(Long timeSpent) {
		this.timeSpent = timeSpent;
	}

	public String getRootCause() {
		return rootCause;
	}

	public void setRootCause(String rootCause) {
		this.rootCause = rootCause;
	}

	public String getRootCauseAnalysis() {
		return rootCauseAnalysis;
	}

	public void setRootCauseAnalysis(String rootCauseAnalysis) {
		this.rootCauseAnalysis = rootCauseAnalysis;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectKey() {
		return projectKey;
	}

	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	/* ####### toString method ####### */
	@Override
	public String toString() {
		return "Issue [id=" + id + ", key=" + key + ", summary=" + summary + ", issuetypeName=" + issuetypeName
				+ ", status=" + status + ", storyPoints=" + storyPoints + ", sprintId=" + sprintId + ", sprintName="
				+ sprintName + ", timeOriginalEstimate=" + timeOriginalEstimate + ", timeEstimate=" + timeEstimate
				+ ", timeSpent=" + timeSpent + ", rootCause=" + rootCause + ", rootCauseAnalysis=" + rootCauseAnalysis
				+ ", projectId=" + projectId + ", projectKey=" + projectKey + ", projectName=" + projectName + "]";
	}
	
	
	/**
	 * Logic to collect nested JSON objects containing the fields of an issue
	 * @param Map representing the fields Object coming from JIRA JSON
	 */
	@SuppressWarnings("unchecked")
	@JsonProperty("fields")
	private void unpackedNested(Map<String, Object> fields) {
		//Summary
		this.summary = (String) fields.get("summary"); 
		
		//Issue type name
		Map<String, String> issueTypeJson = (Map<String, String>) fields.get("issuetype");
		this.issuetypeName = issueTypeJson.get("name");
		
		//Status
		Map<String, String> statusJson = (Map<String, String>) fields.get("status"); 
		this.status = statusJson.get("name");
		
		//Story Points
		this.storyPoints = (Double) fields.get("customfield_10026");
		
		//Sprint
		ArrayList<Map<String, Object>> sprintsJSONList = (ArrayList<Map<String, Object>>) fields.get("customfield_10020");
		List<Sprint> sprintsPOJOList = new ArrayList<Sprint>();
		
		try {
			ObjectMapper objMapper = new ObjectMapper();
			for (Map<String, Object> sprint : sprintsJSONList) {
				Sprint sprintPojo = objMapper.convertValue(sprint, Sprint.class);
				sprintsPOJOList.add(sprintPojo);
			}
			Collections.sort(sprintsPOJOList, Comparator.comparing(Sprint::getStartDate));
			this.sprintId = sprintsPOJOList.get(0).getId();
			this.sprintName = sprintsPOJOList.get(0).getName();
		} catch (NullPointerException e) {
			System.out.println(key + "Sprint was null");//TODO Remove System.out - Replace with logger
		}
		
		//Original Estimate
		try {
			this.timeOriginalEstimate = Long.valueOf((Integer) fields.get("timeoriginalestimate"));
		} catch (NumberFormatException e) {

		} catch (NullPointerException e) {
			
		}
		
		// Time Remaining
		try {
			this.timeEstimate = Long.valueOf((Integer) fields.get("timeestimate"));
		} catch (NumberFormatException e) {
		
		} catch (NullPointerException e) {
			
		}
		
		//Time Spent
		try {
			this.timeSpent = Long.valueOf((Integer) fields.get("timespent"));
		} catch (NumberFormatException e) {
		
		} catch (NullPointerException e) {
			
		}
		
		//Root Cause
		this.rootCause = (String) fields.get("customfield_10031");
		this.rootCauseAnalysis  = (String) fields.get("customfield_10032");
		
		//Project
		Map<String, String> projectJson = (Map<String, String>) fields.get("project"); 
		this.projectId = Long.valueOf((String) projectJson.get("id"));
		this.projectKey = (String) projectJson.get("key");
		this.projectName = (String) projectJson.get("name");
		
	}


}
