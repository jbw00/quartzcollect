package com.hamusuta.quartzcollect.job;

import org.quartz.*;

/**
 * @author Bowen
 * 同步job任务，非必须不建议使用
 *
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public interface StatefulBaseJob extends JobInt {

    String JOB_GROUP = "synchronous";

    /**
     * 若执行定时任务需要实现该方法
     * @param context 传递参数实体
     * @throws JobExecutionException
     */
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;

}
