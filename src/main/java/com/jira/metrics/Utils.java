package com.jira.metrics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {
	
	private static final String JIRA_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	
	public static Date convertJiraDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(JIRA_DATE_PATTERN);
		Date result = formatter.parse(date);
		return result;
	}

}
