package org.zerock.myapp.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class JobA implements Job {

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		
		log.trace("execute({}) invoked.", ctx);
		
		try {
			
			log.info("--------------- JobA invoked. ---------------");
			
		} catch ( Exception e ) {
			
			throw new JobExecutionException (e);
			
		} // try - catch

	} // execute

} // JobA
