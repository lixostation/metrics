package com.jira.metrics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.metrics.domain.History;
import com.jira.metrics.repository.HistoryRepository;

@Service
public class HistoryService {
	@Autowired
	private HistoryRepository historyRepository;
	
	public  void saveHistoryToElastic(List<History> historyList) {
		historyRepository.saveAll(historyList);
	}
}
