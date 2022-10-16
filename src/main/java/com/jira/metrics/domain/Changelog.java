package com.jira.metrics.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Changelog {
	
	private Logger logger = LoggerFactory.getLogger(Changelog.class);
	private final String timeOriginalEstimate = "timeoriginalestimate";
	private final String timeEstimate = "timeestimate";
	private final String timeSpent = "timespent";
	private final String storyPoints = "customfield_10026";
	private final String status = "status";
	
	/* ####### Fields #######*/
	private Long id;
	private String key;
	private List<History> histories = new ArrayList<History>();
	
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

	public List<History> getHistories() {
		return histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}
	
	/* ####### toString method ####### */
	@Override
	public String toString() {
		return "Changelog [key=" + key + ", histories=" + histories + "]";
	}

	
	
	
	@SuppressWarnings("unchecked")
	@JsonProperty("changelog")
	private void unpackedNested(Map<String, Object> changelog) {
		ArrayList<Map<String, Object>> historiesListJson = (ArrayList<Map<String, Object>>) changelog.get("histories");
		try {
			for (Map<String, Object> histories : historiesListJson) {
				History history = new History();
				history.setIssueId(this.id);
				history.setIssueKey(this.key);
				history.setChangelogId(Long.valueOf((String)histories.get("id")));
				history.setCreated(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse((String)histories.get("created")));
				ArrayList<Map<String, String>> items = (ArrayList<Map<String, String>>) histories.get("items");
				for (Map<String, String> item : items) {
					logger.info("item.get(\"fieldId\"): " + item.get("fieldId"));
					if(item.get("fieldId").equals(timeOriginalEstimate)) {
						try {
							history.setTimeOriginalEstimateFrom(Long.valueOf(item.get("fromString")));
						} catch (NumberFormatException e) {
							// Ignore null values as it is possible to be null
						}
						try {
							history.setTimeOriginalEstimateTo(Long.valueOf(item.get("toString")));
						} catch (NumberFormatException e) {
							// Ignore null values as it is possible to be null
						}
					} else if(item.get("fieldId").equals(timeEstimate)) {
						try {
							history.setTimeEstimateFrom(Long.valueOf(item.get("fromString")));
						} catch (NumberFormatException e) {
							// Ignore null values as it is possible to be null
						}
						try {
							history.setTimeEstimateTo(Long.valueOf(item.get("toString")));
						} catch (NumberFormatException e) {
							// Ignore null values as it is possible to be null
						}
					} else if(item.get("fieldId").equals(timeSpent)) {
						try {
							history.setTimeSpentTo(Long.valueOf(item.get("fromString")));
						} catch (NumberFormatException e) {
							// Ignore null values as it is possible to be null
						}
						try {
							history.setTimeSpentFrom(Long.valueOf(item.get("toString")));
						} catch (NumberFormatException e) {
							// Ignore null values as it is possible to be null
						}
					} else if(item.get("fieldId").equals(storyPoints)) {
						try {
							history.setStoryPointsFrom(Double.valueOf(item.get("fromString")));
						} catch (NumberFormatException e) {
							// Ignore null values as it is possible to be null
						}
						try {
							history.setStoryPointsTo(Double.valueOf(item.get("toString")));
						} catch (NumberFormatException e) {
							// Ignore null values as it is possible to be null
						}
						
					} else if(item.get("fieldId").equals(status)) {
						history.setStatusFrom(item.get("fromString"));
						history.setStatusTo(item.get("toString"));
					}
					logger.info("history: " + history);
					
				}
				this.histories.add(history);
				logger.info("[history] entry added on [histories] ArrayList successfully!!");
			}
		} catch (NullPointerException e) {
			logger.debug("There were some null values that can be ignored: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Error looping through histories objects: " + e.getMessage());
			e.printStackTrace(System.out);
		}
		
	}
	
}
