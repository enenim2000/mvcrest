package com.etranzact.mvcrest.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class BatchReportJobScheduler {

	private static final String TRIGGER_NAME = "Graph_Monitor_Batch_Report";
    private static final String GROUP = "Graph_Monitor_Batch_Report_Group";
	private static final String JOB_NAME = "Graph_Monitor_Batch_Report_Job";
	private static Scheduler scheduler;

	public BatchReportJobScheduler(){
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			Trigger trigger =  buildSimpleSchedulerTrigger();
			scheduleJob(trigger);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void scheduleJob(Trigger trigger) throws Exception {
		JobDetail someJobDetail = JobBuilder.newJob(BatchReportJob.class).withIdentity(JOB_NAME, GROUP).build();
		scheduler.scheduleJob(someJobDetail, trigger);
	}

	private static Trigger buildSimpleSchedulerTrigger() {
		//int INTERVAL_SECONDS = 60;
		int INTERVAL_SECONDS = 30;
		return TriggerBuilder.newTrigger().withIdentity(TRIGGER_NAME, GROUP)
				.withSchedule(
						SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(INTERVAL_SECONDS).repeatForever())
				.build();
	}

	private static Trigger buildCronSchedulerTrigger() {
		String CRON_EXPRESSION = "0 * * * * ?";
		return TriggerBuilder.newTrigger().withIdentity(TRIGGER_NAME, GROUP)
				.withSchedule(CronScheduleBuilder.cronSchedule(CRON_EXPRESSION)).build();
	}
}