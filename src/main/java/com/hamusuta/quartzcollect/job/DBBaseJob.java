package com.hamusuta.quartzcollect.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author hamusuta
 */
public interface DBBaseJob extends Job {

    String job_group = "database";

    /**
     * httpjob接口，若定时任务执行http任务需要实现该方法
     * @param context 传递参数实体
     * @throws JobExecutionException
     */
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;

}
