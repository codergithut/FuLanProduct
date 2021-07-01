package fulan.tianjian.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import fulan.tianjian.demo.client.insure.DroolsService;
import fulan.tianjian.demo.quartz.JobFactory;

@Configuration
public class ServiceBeanConfig {
	
	
	@Bean(initMethod = "initKieSession")
	public DroolsService initDroolsService() {
		return new DroolsService();
	}
	
	@Bean
	public SchedulerFactoryBean initScheduler() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setJobFactory(new JobFactory());
		return schedulerFactoryBean;
	}

}
