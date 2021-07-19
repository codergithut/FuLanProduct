package fulan.tianjian.demo.convert;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import fulan.tianjian.demo.convert.model.SourceValue;
import fulan.tianjian.demo.convert.model.TargetValue;
import fulan.tianjian.demo.exception.DrlResourceEmptyException;
import fulan.tianjian.demo.pool.KieSessionManager;

public class DroolBeanConvert<S, T> implements BeanConvertByUserTemplateService<S, T> {
	
	List<DefaultDroolsValue> defaultDroolsValues = new ArrayList<DefaultDroolsValue>();
	
	private KieSessionManager KieSessionManager = new KieSessionManager();
	
	public void initDroolsDefault(Class<?> s, Class<?> t, String droolsContent) {
		if(s == SourceValue.class && t == TargetValue.class) {
			DefaultDroolsValue defaultDroolsValue = new DefaultDroolsValue();
			defaultDroolsValue.setSource(s);
			defaultDroolsValue.setTarget(t);
			defaultDroolsValue.setDroolsContent(droolsContent);
			defaultDroolsValues.add(defaultDroolsValue);
		}
		
	}

	/**
	 * 初始化模板引擎会话信息
	 * 
	 * @param drlResources 模板信息资源
	 * @throws Exception 
	 * @throws DrlResourceEmptyException 模板数据获取失败
	 */
	private KieSession initKieSession(Class<S> s, Class<T> t) throws Exception {
		
		KieSession kieSession = null;

		/**
		 * 如果已有session关闭
		 */
		for(DefaultDroolsValue defaultValue : defaultDroolsValues) {
			if(defaultValue.getSource() == s && defaultValue.getTarget() == t) {
				
				if(defaultValue.getKieSession() != null) {
					kieSession = defaultValue.getKieSession();
				}
				
				String drlContent = defaultValue.getDroolsContent();
				// 池化管理
				kieSession = KieSessionManager.getKieSessionPool(drlContent).borrowObject();
			}
		}
		
		return kieSession;
		

	}

	// 关闭模板引擎的会话记录
	public void closeKieSession(Class<S> sc, Class<T> tc) {
		defaultDroolsValues.stream().forEach(e -> {
			if(e.getSource() == sc && e.getTarget() == tc) {
				e.getKieSession().dispose();
				e.setKieSession(null);
			}
		});
	}

	/**
	 * 转换工具是哦也能够
	 */
	@SuppressWarnings("deprecation")
	@Override
	public T beanConvertBySource(S s, Class<S> sc, Class<T> tc) {

		KieSession kieSession = null;
		try {
			kieSession = initKieSession(sc, tc);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return null;
		}

		T tv = null;

		try {
			tv = tc.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConvertModel<S, T> convertModel = new ConvertModel<S, T>();
		convertModel.setSource(s);
		convertModel.setTarget(tv);

		kieSession.insert(convertModel);
		kieSession.fireAllRules(1);

		return convertModel.getTarget();
	}

	public static void main(String[] args) {
		
		String droolsContent = "package fulan.tianjian.demo.convert\r\n" + "\r\n"
				+ "import fulan.tianjian.demo.convert.model.SourceValue\r\n"
				+ "import fulan.tianjian.demo.convert.model.TargetValue\r\n" + "\r\n" + "rule \"convert Bean File\"\r\n"
				+ "	when\r\n" + "	    $model : ConvertModel()\r\n"
				+ "	    $source : SourceValue() from $model.source\r\n"
				+ "	    $target : TargetValue() from $model.target\r\n" + "    then\r\n"
				+ "        System.out.println(\"OK\");\r\n"
				+ "        $target.setTargetName($source.getSourceName());\r\n"
				+ "        $target.setTargetValue($source.getSourceValue());\r\n" + "end";
		
		DroolBeanConvert<SourceValue, TargetValue> droolBeanConvert = new DroolBeanConvert<SourceValue, TargetValue>();
		
		droolBeanConvert.initDroolsDefault(SourceValue.class, TargetValue.class, droolsContent);
		SourceValue s = new SourceValue();
		s.setSourceName("我是名字");
		s.setSourceValue("目标值");
		TargetValue t = (TargetValue) droolBeanConvert.beanConvertBySource(s, SourceValue.class, TargetValue.class);
		System.out.println(t.getTargetName());
	}

	private class DefaultDroolsValue {

		@SuppressWarnings("rawtypes")
		private Class source;

		@SuppressWarnings("rawtypes")
		private Class target;

		private String droolsContent;

		@SuppressWarnings("rawtypes")
		public Class getSource() {
			return source;
		}
		
		@Autowired
		private KieSession kieSession;

		@SuppressWarnings("rawtypes")
		public void setSource(Class source) {
			this.source = source;
		}

		@SuppressWarnings("rawtypes")
		public Class getTarget() {
			return target;
		}

		@SuppressWarnings("rawtypes")
		public void setTarget(Class target) {
			this.target = target;
		}

		public String getDroolsContent() {
			return droolsContent;
		}

		public void setDroolsContent(String droolsContent) {
			this.droolsContent = droolsContent;
		}

		public KieSession getKieSession() {
			return kieSession;
		}

		public void setKieSession(KieSession kieSession) {
			this.kieSession = kieSession;
		}
		
		
		
		


	}

}
