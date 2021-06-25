package fulan.tianjian.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fulan.tianjian.demo.client.insure.DroolsService;

@Configuration
public class ServiceBeanConfig {
	
	
	@Bean(initMethod = "initKieSession")
	public DroolsService initDroolsService() {
		return new DroolsService();
	}

}
