package org.zerock.myapp.listener;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor
public class SchedulerListenerlmpl implements SchedulerListener {

	@Override
	public void jobScheduled(Trigger trigger) {
		// TODO Auto-generated method stub

	} // jobScheduled(Trigger trigger)

	@Override
	public void jobUnscheduled(TriggerKey triggerKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void triggerFinalized(Trigger trigger) {
		// TODO Auto-generated method stub

	} // triggerFinalized

	@Override
	public void triggerPaused(TriggerKey triggerKey) {
		// TODO Auto-generated method stub

	} // triggerPaused

	@Override
	public void triggersPaused(String triggerGroup) {
		// TODO Auto-generated method stub

	} // triggersPaused

	@Override
	public void triggerResumed(TriggerKey triggerKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void triggersResumed(String triggerGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jobDeleted(JobKey jobKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jobPaused(JobKey jobKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jobsPaused(String jobGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jobResumed(JobKey jobKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void jobsResumed(String jobGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		
		log.info("schedulerError() invoked.");
		log.trace("schedulerError({}, {}) invoked.", msg, cause);

	} // schedulerError : 오류가 났을 때

	@Override
	public void schedulerInStandbyMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void schedulerStarted() {
		// TODO Auto-generated method stub

	}

	@Override
	public void schedulerStarting() {
		
		log.info("schedulerStarting() invoked.");

	} // schedulerStarting

	@Override
	public void schedulerShutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void schedulerShuttingdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void schedulingDataCleared() {
		// TODO Auto-generated method stub

	}

} // end class
