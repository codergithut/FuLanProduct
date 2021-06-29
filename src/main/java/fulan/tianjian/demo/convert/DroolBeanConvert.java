package fulan.tianjian.demo.convert;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.convert.model.SourceValue;
import fulan.tianjian.demo.convert.model.TargetValue;
import fulan.tianjian.demo.exception.DrlResourceEmptyException;

public class DroolBeanConvert<S, T> implements BeanConvertByUserTemplateService<S, T> {

	@Autowired
	private KieSession kieSession;
	
	List<DefaultDroolsValue> defaultDroolsValues = new ArrayList<DefaultDroolsValue>();
	
	public void initDroolsDefault(Class<?> s, Class<?> t, String droolsContent) {
		if(s == SourceValue.class && t == TargetValue.class) {
			DefaultDroolsValue defaultDroolsValue = new DefaultDroolsValue();
			defaultDroolsValue.setSource(s);
			defaultDroolsValue.setTarget(t);
			defaultDroolsValue.setDroolsContent(droolsContent);
			defaultDroolsValues.add(defaultDroolsValue);
		}
		
	}

	@Override
	public String getTemplateContent(Class<S> s, Class<T> t) {
		
		if(CollectionUtils.isEmpty(defaultDroolsValues)) {
			return null;
		}
		
		
		for(DefaultDroolsValue defaultValue : defaultDroolsValues) {
			if(defaultValue.getSource() == s && defaultValue.getTarget() == t) {
				return defaultValue.getDroolsContent();
			}
		}
		return null;
	}

	/**
	 * 初始化模板引擎会话信息
	 * 
	 * @param drlResources 模板信息资源
	 * @throws DrlResourceEmptyException 模板数据获取失败
	 */
	private void initKieSession(String drlContent) {

		/**
		 * 如果已有session关闭
		 */
		closeKieSession();

		if (kieSession != null) {
			return;
		}

		// 初始化知识库获并取会话信息
		KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
		Resource resource = ResourceFactory.newReaderResource(new StringReader(drlContent.replaceAll("\r", "")));
		kb.add(resource, ResourceType.DRL);
		KieBase kieBase = kb.newKieBase();
		kieSession = kieBase.newKieSession();

	}

	// 关闭模板引擎的会话记录
	public void closeKieSession() {
		if (kieSession == null) {
			return;
		}
		kieSession.dispose();
	}

	/**
	 * 转换工具是哦也能够
	 */
	@SuppressWarnings("deprecation")
	@Override
	public T beanConvertBySource(S s, Class<S> sc, Class<T> tc) {

		initKieSession(getTemplateContent(sc, tc));

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
		
		


	}

}
