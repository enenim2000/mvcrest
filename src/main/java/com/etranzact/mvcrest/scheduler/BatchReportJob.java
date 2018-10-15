package com.etranzact.mvcrest.scheduler;

import java.time.LocalDateTime;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class BatchReportJob implements Job {

	@Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
		LocalDateTime localTime = LocalDateTime.now();
		System.out.println("Run BatchReportJob at " + localTime.toString());
	}

}