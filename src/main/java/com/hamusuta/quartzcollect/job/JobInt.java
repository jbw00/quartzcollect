package com.hamusuta.quartzcollect.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface JobInt extends Job{

    @Override
    void execute(JobExecutionContext context) throws JobExecutionException;
}
