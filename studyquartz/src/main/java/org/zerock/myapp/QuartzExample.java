package org.zerock.myapp;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ListenerManager;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.zerock.myapp.job.JobA;
import org.zerock.myapp.job.JobB;
import org.zerock.myapp.job.SimpleJob;
import org.zerock.myapp.listener.JobListenerImpl;
import org.zerock.myapp.listener.SchedulerListenerImpl;
import org.zerock.myapp.listener.TriggerListenerImpl;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class QuartzExample {

	
    public static void main(String args[]) throws InterruptedException {

        try {

        	// ==============================================================
        	// 1. To create a Quartz job scheduler
        	// ==============================================================
        	
        	Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();


        	// ==============================================================
        	// 2. To create a Quartz job with the associated trigger #1
        	// ==============================================================
        	
        	// 2-1. Quartz Job to be scheduled.
            JobDetail simpleJob = 
    			JobBuilder.newJob( SimpleJob.class ).
	    			withIdentity("simpleJob", "GROUP1").
//    				withIdentity( JobKey.jobKey("simpleJob", "GROUP1") ).
	    			usingJobData("jobSays", "Hello World!").
	    			usingJobData("myFloatValue", 3.141f).
	    			build();
            
//            ---

            // 2-2. Job Scheduling registered to the Quartz Scheduler.
            Trigger simpleJobTrigger = 
        		TriggerBuilder.newTrigger().
        			withIdentity("simpleJobTrigger", "GROUP1").
//        			withIdentity( TriggerKey.triggerKey("simpleJobTrigger", "GROUP1") ).
        			startNow().
        			withSchedule( 
    					SimpleScheduleBuilder.simpleSchedule().
//							withIntervalInMilliseconds(1000*1L).	// in milliseconds
							withIntervalInSeconds(1).				// in seconds
//    						withIntervalInMinutes(1). 				// in minutes
//    						withIntervalInHours(1).					// in hours
							repeatForever()
							
//    						withRepeatCount(10)
    				).
        			build();


        	// ==============================================================
            // 2. To create a Quartz job with the associated trigger #2
        	// ==============================================================

            // 2-1. Quartz Job to be scheduled.
            JobDetail jobA = 
        		JobBuilder.newJob(JobA.class).
//        			withIdentity("jobA", "GROUP2").
        			withIdentity( JobKey.jobKey("jobA", "GROUP2") ).
        			build();
            
//          ---

            // 2-2. Job Scheduling registered to the Quartz Scheduler.
            Trigger jobATrigger = 
        		TriggerBuilder.newTrigger().
//        			withIdentity("jobATrigger", "GROUP2").
        			withIdentity( TriggerKey.triggerKey("jobATrigger", "GROUP2") ).
        			startNow().
        			withPriority(15).
        			withSchedule(
    					SimpleScheduleBuilder.simpleSchedule().
    						withIntervalInSeconds(40).
    						repeatForever()
    				).
        			build();


        	// ==============================================================
            // 2. To create a Quartz job with the associated trigger #3
        	// ==============================================================
            
            // 2-1. Quartz Job to be scheduled.
            JobDetail jobB = 
        		JobBuilder.newJob(JobB.class).
//        			withIdentity("jobB", "GROUP2").
        			withIdentity( JobKey.jobKey("jobB", "GROUP2") ).
        			build();
            
//          ---

            // 2-2. Job Scheduling registered to the Quartz Scheduler.
            Trigger jobBTrigger = 
        		TriggerBuilder.newTrigger().
//        			withIdentity("jobBTrigger", "GROUP2").
        			withIdentity( TriggerKey.triggerKey("jobBTrigger", "GROUP2") ).
        			startNow().
        			withPriority(10).
        			withSchedule(
    					SimpleScheduleBuilder.simpleSchedule().
    						withIntervalInSeconds(20).
    						repeatForever()
    				).
        			build();

            
        	// ==============================================================
            // 3. To add each listener with Quartz job, trigger, scheduler
        	// ==============================================================
            
            ListenerManager listenerManager = scheduler.getListenerManager();
            
            listenerManager.addJobListener(new JobListenerImpl());
            listenerManager.addTriggerListener(new TriggerListenerImpl());
            listenerManager.addSchedulerListener(new SchedulerListenerImpl());
        	

        	// ==============================================================
            // 4. To schedule Quartz Jobs with JobDetail and Trigger
        	// ==============================================================
            
            scheduler.scheduleJob(simpleJob, 	simpleJobTrigger);
            scheduler.scheduleJob(jobA, 		jobATrigger);
            scheduler.scheduleJob(jobB, 		jobBTrigger);


        	// ==============================================================
            // 5. To start a Quartz Scheduler
        	// ==============================================================
            
            scheduler.start();
            
            log.info("Quartz Job Scheduler Started: {}", scheduler.isStarted());

        } catch (SchedulerException e) {
            e.printStackTrace();
        } // try-catch
        
    } // main

    
} // end class