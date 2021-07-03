package fulan.tianjian.demo.quartz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.quartz.proxy.CglibProxyFactory;

@Service
public class TaskExecuteFactory {

	
	private Map<String, TaskExecute> registerTaskExecutes = new HashMap<String,TaskExecute>();
	
	/**
	 * 获取项目所有实现了TaskExecute的接口
	 */
	@Autowired
	private List<TaskExecute> taskExecutes;
	
	@Autowired
	private CronInstanceCurd cronInstanceCurd;
	
	@PostConstruct
	public void init() {
		CglibProxyFactory.setCronInstanceCurd(cronInstanceCurd);
	}
	
	public TaskExecute createTaskExecuteByKey(String key) {
		
		TaskExecute proxy = null;
		
		if(registerTaskExecutes.containsKey(key)) {
			return registerTaskExecutes.get(key);
		}
		
		TaskExecute taskExecute = null;
		if(CollectionUtils.isEmpty(taskExecutes)) {
			return null;
		}
		
		for(TaskExecute detail : taskExecutes) {
			if(key.equals(detail.getQuartzTaskKey())) {
				taskExecute = detail;
			}
		}
		
		if(taskExecute != null) {

	        CglibProxyFactory cglibProxy = new CglibProxyFactory(taskExecute);
	        //jdk需要提供接口，cglib需要是非私有类，且不能处理final关键字修饰的方法
	        Enhancer enhancer = new Enhancer();
	        //设置父类
	        enhancer.setSuperclass(TaskExecute.class);
	        //设置回调对象
	        enhancer.setCallback(cglibProxy);

	        proxy = (TaskExecute) enhancer.create();
	        
	        registerTaskExecutes.put(key, proxy);
	        
		}
		return proxy;
	}
	
	

}
