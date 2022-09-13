package org.zerock.myapp.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class SimpleJob implements Job {

	
	@Override
    public void execute(JobExecutionContext ctx) throws JobExecutionException {
		log.trace("execute({}) invoked.", ctx);
		
		try {
	        JobDataMap dataMap = ctx.getJobDetail().getJobDataMap();
	
	        String jobSays = dataMap.getString("jobSays");
	        float myFloatValue = dataMap.getFloat("myFloatValue");
	
	        log.info("- SimpleJob says: {}, and val is: {}", jobSays ,myFloatValue);
		} catch(Exception e) {
			throw new JobExecutionException(e);
		} // try-catch
    } // execute
	
} // end class
