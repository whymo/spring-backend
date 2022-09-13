package org.zerock.myapp.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class JobB implements Job {

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		
		log.trace("execute({}) invoked.", ctx);
		
		try {
			
			log.info("--------------- JobB invoked. ---------------");
			
			// JobDetail에서 넘겨준 데이터를 작업 처리에 사용
			JobDataMap map = ctx.getJobDetail().getJobDataMap();
			
			// JobDetail에서 넘겨준 데이터에서 Value 빼오기
			String key1 = (String)map.get("KEY_1");
			String key2 = map.getString("KEY_2");
			String key3 = map.getString("KEY_3");
			
			log.info("\t + Key1 : {}, Key2 :{}, Key3 : {}", key1, key2, key3);
			
		} catch ( Exception e ) {
			
			throw new JobExecutionException (e);
			
		} // try - catch

	} // execute

} // JobB
