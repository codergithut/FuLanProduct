package fulan.tianjian.demo.convert;

/**
 * 转换模型类
 * @author 14681
 *
 * @param <S> 源数据
 * @param <T> 目标数据
 */
public class ConvertModel<S, T> {
	
	
	private S source;
	
	private T target;

	public S getSource() {
		return source;
	}

	public void setSource(S source) {
		this.source = source;
	}

	public T getTarget() {
		return target;
	}

	public void setTarget(T target) {
		this.target = target;
	}
	
	

}
