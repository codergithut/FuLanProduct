package fulan.tianjian.demo.convert;

import java.io.StringReader;

import org.kie.api.KieBase;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fulan.tianjian.demo.exception.DrlResourceEmptyException;

public class DroolBeanConvert<S,T> implements BeanConvertByUserTemplateService<S, T>{
	
	@Autowired
	private KieSession kieSession;
	
	@Override
	public String getTemplateContent() {
		String drlContents = "package fulan.tianjian.demo.convert\r\n"
				+ "\r\n"
				+ "import fulan.tianjian.demo.convert.SourceValue\r\n"
				+ "import fulan.tianjian.demo.convert.TargetValue\r\n"
				+ "\r\n"
				+ "rule \"convert Bean File\"\r\n"
				+ "	when\r\n"
				+ "	    $model : ConvertModel()\r\n"
				+ "	    $source : SourceValue() from $model.source\r\n"
				+ "	    $target : TargetValue() from $model.target\r\n"
				+ "    then\r\n"
				+ "        System.out.println(\"OK\");\r\n"
				+ "        $target.setTargetName($source.getSourceName());\r\n"
				+ "        $target.setTargetValue($source.getSourceValue());\r\n"
				+ "end";
		return drlContents;
	}

	
	/**
	 * 初始化模板引擎会话信息
	 * @param drlResources 模板信息资源
	 * @throws DrlResourceEmptyException 模板数据获取失败
	 */
	private void initKieSession(String drlContent) {
		
		/**
		 * 如果已有session关闭
		 */
		closeKieSession();
		
		if(kieSession != null) {
			return ;
		}
		
		//初始化知识库获并取会话信息
		KnowledgeBuilder  kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
		Resource resource = ResourceFactory.newReaderResource(new StringReader(drlContent.replaceAll("\r", "")));
		kb.add(resource, ResourceType.DRL);
		KieBase kieBase = kb.newKieBase();
		kieSession = kieBase.newKieSession();
		
	}
	

	//关闭模板引擎的会话记录
	public void closeKieSession() {
		if(kieSession == null) {
			return ;
		}
		kieSession.dispose();
	}

	/**
	 * 转换工具是哦也能够
	 */
	@SuppressWarnings("deprecation")
	@Override
	public T beanConvertBySource(S s, Class<T> tc) {
		
		initKieSession(getTemplateContent());
		
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
		DroolBeanConvert<SourceValue, TargetValue> droolBeanUtil = new DroolBeanConvert<SourceValue, TargetValue>();
		SourceValue s = new SourceValue();
		s.setSourceName("我是名字");
		s.setSourceValue("目标值");
		TargetValue t = droolBeanUtil.beanConvertBySource(s, TargetValue.class);
		System.out.println(t.getTargetName());
	}
	
	
	

}
