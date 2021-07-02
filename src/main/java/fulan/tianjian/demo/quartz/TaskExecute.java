package fulan.tianjian.demo.quartz;


public interface TaskExecute {
	
    String getQuartzTaskKey();
	
	Boolean quartzTask(String params);

}
