package com.hamusuta.quartzcollect.runner;

import com.hamusuta.quartzcollect.job.BaseJob;
import com.hamusuta.quartzcollect.trigger.triggerimpl.CronTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author hamusuta
 */
@Component
public class ScheduleRunner implements ApplicationRunner {

    @Autowired
    private CronTrigger cronTrigger;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //TODO-bw 查询启用状态的任务并执行




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
        //jobDetail
        JobDetail jobDetail = getJobDetail(jobClassName, jobName, jobGroup);
        //Trigger
        //TODO-bw 加入字段判断触发器类型从而实现不同的触发方式
        Trigger trigger = cronTrigger.triggerBuilder(triggerName, triggerGroup, cronExpression);
        try{
            //创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        }catch (Exception e){
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
    private JobDetail getJobDetail(String jobClassName, String jobName, String jobGroup){
        JobDetail jobDetail = null;
        try {
            //获取job实现类并转化
            Class<? extends BaseJob> aClass = (Class<? extends BaseJob>) Class.forName(jobClassName);
            //创建job任务
            jobDetail = JobBuilder.newJob(aClass).withIdentity(jobName, jobGroup).build();
        }catch (Exception e){
            throw new RuntimeException("无法加载"+jobClassName+"类别!");
        }
        return jobDetail;
    }

}
