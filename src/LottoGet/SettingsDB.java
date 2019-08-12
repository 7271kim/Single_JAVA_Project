package LottoGet;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


public class SettingsDB {
    
    public static void main(String[] args) throws Exception {
        
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        try {
            JobDetail job = JobBuilder.newJob(SettingsLottoDataWeek.class).withIdentity("SettingsLottoDataWeek","group1").build();
            CronTrigger trigger = TriggerBuilder.newTrigger()
                                                .withIdentity("trigger1", "group1")
                                                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 1-2 ? * SUN"))
                                                .forJob("SettingsLottoDataWeek", "group1")
                                                .build();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch(Exception e) {
            e.printStackTrace();
        }        
        
    }
}

