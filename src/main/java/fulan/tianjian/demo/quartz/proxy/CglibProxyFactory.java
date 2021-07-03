package fulan.tianjian.demo.quartz.proxy;

import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import fulan.tianjian.demo.quartz.CronInstanceCurd;
import fulan.tianjian.demo.quartz.CronInstanceEo;
import fulan.tianjian.demo.quartz.QuartzClientRequest;

public class CglibProxyFactory implements MethodInterceptor {

	private static CronInstanceCurd cronInstanceCurd;

	private Object target;

	public CglibProxyFactory(Object target) {
		super();
		this.target = target;
	}

	public static void setCronInstanceCurd(CronInstanceCurd cronInstanceCurd) {
		CglibProxyFactory.cronInstanceCurd = cronInstanceCurd;
	}

	/**
	 * 1、代理对象；2、委托类方法；3、方法参数；4、代理方法的MethodProxy对象。
	 *
	 * @param o
	 * @param method
	 * @param objects
	 * @param methodProxy
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("事务开始......" + method.getName());
		QuartzClientRequest quartzClientRequest = (QuartzClientRequest) objects[0];

		// 异步调用请求任务 根据定时任务返回结果插入信息到定时任务执行表中
		CompletableFuture.supplyAsync(() -> {
			try {
				return (Boolean) methodProxy.invoke(target, objects);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}).thenAccept((result) -> {
			CronInstanceEo v = cronInstanceCurd.findByCronMetadataIdAndJobInsCode(quartzClientRequest.getCronMetadataId(),
					quartzClientRequest.getJobInsId());
			if (result && v != null) {
				v.setStage("finished");
				v.setIsSuccess(true);
			} else {
				v.setStage("unFinished");
				v.setIsSuccess(false);
			}
			cronInstanceCurd.save(v);
		});
		return true;
	}
}
