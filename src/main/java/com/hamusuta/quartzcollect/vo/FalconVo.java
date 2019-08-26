package com.hamusuta.quartzcollect.vo;

public class FalconVo {

    /**
    *指标主体标识
    */
    private String endpoint;

    /**
     *指标唯一标识
     */
    private String metric;

    /**
     *metric属性描述（eg：idc=lg，service=xbox，多个tag之间用逗号分割）
     */
    private String tags;

    /**
     *步长（单位秒）
     */
    private Integer step;

    /**
     *推送时间
     */
    private String timestamp;

    /**
     *指标值
     */
    private Double value;

    /**
     *计时器类型
     */
    private String counterType;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getCounterType() {
        return counterType;
    }

    public void setCounterType(String counterType) {
        this.counterType = counterType;
    }

}
