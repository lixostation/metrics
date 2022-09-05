package com.jira.metrics.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "sprints")
public class Sprint {
	/* ####### Fields ####### */
	@JsonProperty("id")
	@Id
	private Long id;
	
	@JsonProperty("name")
	@Field(type = FieldType.Text, name = "name")
	private String name;
	
	@JsonProperty("state")
	@Field(type = FieldType.Text, name = "state")
	private String state;
	
	@JsonProperty("startDate")
	@Field(type = FieldType.Date, name = "startDate")
	private Date startDate;
	
	@JsonProperty("endDate")
	@Field(type = FieldType.Date, name = "endDate")
	private Date endDate;
	
	/* ####### Getters and Setters #######*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/* ####### toString method ####### */
	@Override
	public String toString() {
		return "Sprint [id=" + id + ", name=" + name + ", state=" + state + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	
}
