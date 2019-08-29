package com.hamusuta.quartzcollect.servic;

import com.github.pagehelper.PageInfo;
import com.hamusuta.quartzcollect.modle.JobDetail;
import com.hamusuta.quartzcollect.vo.JobAndTriggerVo;

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

    /**
     * 获取job关联Trigger的详细信息
     * @return
     */
    PageInfo<JobAndTriggerVo> getJobAndTriggerDetails(Integer pageNum, Integer pageSize);

}
