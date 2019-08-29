package com.hamusuta.quartzcollect.modle;

import java.util.Date;

public class JobDetail {
    private Integer jobId;

    private String jobName;

    private String jobGroup;

    private Integer jobType;

    private String jobMetrics;

    private String jobClassName;

    private Integer jobTrigger;

    private Integer jobStatus;

    private Date creatTime;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public String getJobMetrics() {
        return jobMetrics;
    }

    public void setJobMetrics(String jobMetrics) {
        this.jobMetrics = jobMetrics == null ? null : jobMetrics.trim();
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName == null ? null : jobClassName.trim();
    }

    public Integer getJobTrigger() {
        return jobTrigger;
    }

    public void setJobTrigger(Integer jobTrigger) {
        this.jobTrigger = jobTrigger;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}