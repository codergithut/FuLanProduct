package fulan.tianjian.demo.quartz;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.*;
public class Test {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        
        //创建一个scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getContext().put("skey", "svalue");
        
        //创建一个Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .usingJobData("t1", "tv1")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3)
                        .repeatForever()).build();
        trigger.getJobDataMap().put("t2", "tv2");
        
        
        //创建一个job
        JobDetail job = JobBuilder.newJob(RestClient.class)
                    .usingJobData("j1", "jv1")
                    .withIdentity("myjob", "mygroup").build();
        job.getJobDataMap().put("j2", "jv2");
        
        //注册trigger并启动scheduler
        scheduler.scheduleJob(job,trigger);
        scheduler.start();
        
        Thread.sleep(100000L);
        
        scheduler.deleteJob(job.getKey());
        
    }

}