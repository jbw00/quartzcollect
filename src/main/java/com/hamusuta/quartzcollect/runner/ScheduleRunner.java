package com.hamusuta.quartzcollect.runner;

import com.hamusuta.quartzcollect.job.BaseJob;
import com.hamusuta.quartzcollect.modle.TriggerDetail;
import com.hamusuta.quartzcollect.servic.JobService;
import com.hamusuta.quartzcollect.servic.TriggerService;
import com.hamusuta.quartzcollect.trigger.triggerimpl.CronTrigger;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hamusuta
 */
@Component
public class ScheduleRunner implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(ScheduleRunner.class);

    @Autowired
    private CronTrigger cronTrigger;
    //TODO-bw 预留simpletrigger的引入
    /*@Autowired
    private Simpletrigger simpletrigger;*/
    @Autowired
    private JobService jobService;
    @Autowired
    private TriggerService triggerService;
    /**加入Qulifier注解，通过名称注入bean*/
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    private static final String CRON = "cron";
    private static final String SIMPLE = "simple";

    @Override
    @Order(3)
    public void run(ApplicationArguments args) throws Exception {
        //TODO-bw 查询启用状态的任务并执行 数量巨大时改造成rdis存取
        List<com.hamusuta.quartzcollect.modle.JobDetail> usefulJobDetail = jobService.getUsefulJobDetail();
        for (com.hamusuta.quartzcollect.modle.JobDetail jobDetail : usefulJobDetail) {
            Integer jobTriggerId = jobDetail.getJobTrigger();
            TriggerDetail triggerDetail = triggerService.getTriggerDetailById(jobTriggerId);
            //执行job任务
            runJob(jobDetail.getJobClassName(),
                    jobDetail.getJobName(),
                    jobDetail.getJobGroup(),
                    triggerDetail.getTriggerName(),
                    triggerDetail.getTriggerGroup(),
                    triggerDetail.getCronExpression(),
                    jobDetail.getJobMetrics()
                    );
        }
    }

    /**
     * 构建单个定时任务
     * @param parms 顺序固定 jobClassName ：实现job的类名 jobName：任务名 jobGroup：任务组 triggerName：触发器名 triggerGroup：触发器组 cronExpression：表达式
     */
    private void runJob(String... parms){
        //解析入参
        ArrayList parmsList = new ArrayList(Arrays.asList(parms));
        String jobClassName = String.valueOf(parmsList.get(0));
        String jobName = String.valueOf(parmsList.get(1));
        String jobGroup = String.valueOf(parmsList.get(2));
        String triggerName = String.valueOf(parmsList.get(3));
        String triggerGroup = String.valueOf(parmsList.get(4));
        String cronExpression = String.valueOf(parmsList.get(5));
        String metric = String.valueOf(parmsList.get(6));
        //jobDetail
        JobDetail jobDetail = getJobDetail(jobClassName, jobName, jobGroup, metric);
        //Trigger
        Trigger trigger = null;
        //TODO-bw 加入字段判断触发器类型从而实现不同的触发方式
        if(CRON.equals(triggerGroup)) {
            trigger = cronTrigger.triggerBuilder(triggerName, triggerGroup, cronExpression);
        }else if(SIMPLE.equals(triggerGroup)){
            //TODO-bw 预留simpletrigger的构建
        }
        try{
            //创建scheduler
            //Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();该创建为默认创建方式
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        }catch (Exception e){
            logger.error("===========================>"+ e);
            throw new RuntimeException("构建定时器失败！");
        }
    }

    /**
     * 获取JobDetail
     * @param jobClassName job实现类名
     * @param jobName job名
     * @param jobGroup job组别
     * @return
     */
    private JobDetail getJobDetail(String jobClassName, String jobName, String jobGroup, String metric){
        JobDetail jobDetail = null;
        try {
            //获取job实现类并转化
            Class<?> naClass = Class.forName("com.hamusuta.quartzcollect.job.jobimpl."+ jobClassName);

            Class<? extends BaseJob> aClass = (Class<? extends BaseJob>) naClass.newInstance().getClass();
            //创建job任务
            jobDetail = JobBuilder.newJob(aClass).withIdentity(jobName, jobGroup).usingJobData("JOB_METRIC", metric).build();
        }catch (Exception e){
            logger.error("===============================>"+e);
            throw new RuntimeException("无法加载"+jobClassName+"类别!");
        }
        return jobDetail;
    }

}
