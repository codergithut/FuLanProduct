package fulan.tianjian.demo.convert;

public interface BeanConvertByUserTemplateService<S, T>  {
	
	String getTemplateContent();
	
	T beanConvertBySource(S s, Class<T> tc);
	
}
