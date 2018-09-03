package com.yangzai.boot.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableAsync
@EnableScheduling
public class ThreadConfig extends AsyncConfigurerSupport implements SchedulingConfigurer{
	@Autowired
	private BeanFactory beanFactory;

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(7);
		executor.setMaxPoolSize(42);
		executor.setQueueCapacity(11);
		executor.setThreadNamePrefix("MyExecutor-");
		executor.initialize();
		return new LazyTraceExecutor(beanFactory, executor);
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		scheduledTaskRegistrar.setScheduler(schedulingExecutor());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor schedulingExecutor() {
		return Executors.newScheduledThreadPool(1);
	}
}