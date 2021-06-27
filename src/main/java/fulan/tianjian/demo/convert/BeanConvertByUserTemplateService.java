package fulan.tianjian.demo.convert;

/**
 * bean 通过模板进行转换
 * @author 14681
 *
 * @param <S> 源数据
 * @param <T> 转换数据
 */
public interface BeanConvertByUserTemplateService<S, T>  {
	
	String getTemplateContent();
	
	T beanConvertBySource(S s, Class<T> tc);
	
}
