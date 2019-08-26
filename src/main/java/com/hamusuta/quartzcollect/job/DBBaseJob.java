package com.hamusuta.quartzcollect.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author hamusuta
 */
public interface DBBaseJob extends Job {

    String JOB_GROUP = "database";

    /**
     * 若执行定时任务需要实现该方法
     * @param context 传递参数实体
     * @throws JobExecutionException
     */
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;

}
