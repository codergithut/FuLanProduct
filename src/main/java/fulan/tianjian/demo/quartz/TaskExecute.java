package fulan.tianjian.demo.quartz;


/**
 * 定时任务执行器
 * @author 14681
 *
 */
public interface TaskExecute {
	
	/**
	 * 获取任务key 
	 * @return
	 */
    String getQuartzTaskKey();
	
    /**
     * 获取任务返回结果
     * @param quartzClientRequest 定时任务请求数据
     * @return
     */
    Boolean quartzTask(QuartzClientRequest quartzClientRequest);

}
