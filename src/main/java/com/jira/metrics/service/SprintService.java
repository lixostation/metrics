package com.jira.metrics.service;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.metrics.domain.Sprint;
import com.jira.metrics.repository.SprintRepository;

@Service
public class SprintService {
	/**
	 * LinkedHashMap used to store the unique list of Sprints to be saved to Elasticsaerch. 
	 * */
	public static LinkedHashMap<Long, Sprint> sprintsToSave = new LinkedHashMap<Long, Sprint>();
	
	@Autowired
	private SprintRepository sprintRepository;
	
	public void saveSprintsToElastic() {
		sprintRepository.saveAll(sprintsToSave.values());
	}
}
