package com.mooneyserver.scheduler;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TaskFactory {

	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	public DiffusionHealthCheckTask createDiffusionHealthCheckTask() {
		return new DiffusionHealthCheckTask();
	}
}
