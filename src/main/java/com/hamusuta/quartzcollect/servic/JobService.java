package com.hamusuta.quartzcollect.servic;

import com.hamusuta.quartzcollect.modle.JobDetail;

import java.util.List;

/**
 * @author hamusuta
 */
public interface JobService {

    /**
     * 获取激活状态的job信息
     * @return
     */
    List<JobDetail> getUsefulJobDetail();

}
