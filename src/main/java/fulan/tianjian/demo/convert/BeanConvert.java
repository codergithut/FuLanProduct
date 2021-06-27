package fulan.tianjian.demo.convert;

public interface BeanConvert<S, T>  {
	
	String getTemplateContent();
	
	T beanConvertBySource(S s, Class<T> tc);
	
}
