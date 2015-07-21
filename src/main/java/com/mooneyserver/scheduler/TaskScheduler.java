package com.mooneyserver.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskScheduler {
	
	@Autowired
	private TaskFactory taskFactory;

	@Scheduled(fixedRate = 5_000)
	public void doSomething() {
		try {
			taskFactory.createDiffusionHealthCheckTask().performTask();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
