package org.zerock.myapp.server;

import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ListenerManager;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.DailyCalendar;
import org.zerock.myapp.job.JobA;
import org.zerock.myapp.job.JobB;
import org.zerock.myapp.listener.SchedulerListenerlmpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SchedulingServer {

	// 우리가 생성한 3개의 Job을 스케줄링해서 실행시킨다.
	public static void main(String[] args) {
		
		log.trace("SchedulingServer invoked.");
		
		try {
			
			// ================================================
			// 1단계 : 'Scheduler' 인터페이스의 구현객체 획득
			// ================================================
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			log.info("\t + 1. scheduler : {}", scheduler);
			
			
			// ================================================
			// 2단계 : JobA를 위한 'JobDetail' 인터페이스의 구현객체 생성
			// ================================================
			
			// 2 - 1. 쿼츠 JOB Detail to be scheduled
			// + JobDetail은 JobA만을 위한 것이기에, 재사용은 불가능하다.
			JobDetail jobADetail =
					JobBuilder.newJob(JobA.class).
					withIdentity("JobA", "GROUP1"). // withIdentity(Job name, Job 소속 group) Job의 이름과 Job의 소속이름을 생성
					withDescription("JOB A"). // withDescription는 이에 대한 설명을 작성할 수 있다.
					usingJobData("KEY1", "VALUE1").
					usingJobData("KEY2", "VALUE2").
					build(); // .build는 JobDetail 객체를 만들어 준다.
			
			// 2 - 2. 트리거는 잡 스케쥴을 어떻게 수행시킬지 정해준다. ( 스케줄링 정보 )
			// + 트리거는 JobA를 위한 트리거가 아니기에, 재사용이 가능하다.
			// + Job Scheduling registered to the Quartz Scheduler
			Trigger jobATrigger = 
					TriggerBuilder.
					newTrigger(). // 새로운 트리거 생성
					withIdentity("JobA Trigger", "GROUP1"). // 트리거의 이름 생성
					withDescription("Scheduling for JobA"). // 트리거 설명
					withPriority(15). // 트리거의 우선순위
					startNow(). // 지금 시작해라
					withSchedule( SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever() ). // 반복되는 시간과 횟수를 지정
					build();
			
			// + 이러한 트리거를 통해 구동 시에는 1초마다 무한으로 반복해서 수행된다.
			// + repeatForever()대신 다른 것을 넣어 제한적인 횟수만 반복되게 할 수도 있다.
			// + 횟수는 시작하는 횟수를 제외한 횟수로 10을 지정했을 시에는 처음 시작 1번 + 반복횟수 10번이 찍힌다.
			
			
			// ================================================
			// 2단계 : JobB를 위한 'JobDetail' & jobTrigger 인터페이스의 구현객체 생성
			// ================================================
			
			// jobB를 위한 상세 정보
			JobDetail jobBDetail = 
					JobBuilder.
					newJob(JobB.class).
					withIdentity(JobKey.jobKey("JobB", "GROUP1")).
					withDescription("Job Description for JobB").
					usingJobData("KEY_1","VALUE_1").
					usingJobData("KEY_2","VALUE_2").
					usingJobData("KEY_3","VALUE_3").
					build();
			
			// jobB 트리거
			Trigger jobBTrigger = 
					TriggerBuilder.
					newTrigger().
					withIdentity(TriggerKey.triggerKey("JobB Trigger", "GROUP1")).
					withDescription("Scheduling for JobB").
					startNow().								// 어느때 시작할까? (***)
					withSchedule( 							// 시작이후에 어떻게 재기동할까? (****)
							DailyTimeIntervalScheduleBuilder.
							dailyTimeIntervalSchedule().
							withInterval(3, IntervalUnit.SECOND). // 3초 간격으로 ( 여기에서는 밀리세컨드가 불가능 ) 
							withRepeatCount(3) 				// 반복 횟수
					).
					build();
			
			
			// ================================================
			// 3단계 : Scheduler에 JobA / jobADetail / jobATrigger 등록
			// + Scheduler를 이용하여 지정된 Job을 지정한 대로 schedule 등록
			// + JobA는 jobADetail이 가지고 있다.
			// ================================================
			scheduler.scheduleJob(jobADetail , jobATrigger);
			scheduler.scheduleJob(jobBDetail , jobBTrigger);
			
			// ================================================
			// 4단계 : Listener 객체를 생성 및 등록하여,
			// + scheduler / Job / Trigger 이벤트를 모니터링할 수 있다.
			// + SchedulerListener을 implement하는 리스너를 생성해야 한다. (***)
			// ================================================
			ListenerManager lm = scheduler.getListenerManager();
			lm.addSchedulerListener(new SchedulerListenerlmpl());
			
			// ================================================
			// 5단계 : 3단계에 등록한 jobADetail / jobATrigger대로 Job을 수행
			// ================================================
			scheduler.start();
			
		} catch (SchedulerException e) {
			
			// 발생한 예외를 출력하고 정상 종료를 시킨다.
			e.printStackTrace();
			
		} finally {
			log.info("Done!");
		} // try - catch - finally

	} // main

} // end class
