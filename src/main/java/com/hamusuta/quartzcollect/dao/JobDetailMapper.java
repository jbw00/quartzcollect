package com.hamusuta.quartzcollect.dao;

import com.hamusuta.quartzcollect.modle.JobDetail;
import com.hamusuta.quartzcollect.modle.JobDetailExample;
import java.util.List;

import com.hamusuta.quartzcollect.vo.JobAndTriggerVo;
import org.apache.ibatis.annotations.Param;

public interface JobDetailMapper {
    long countByExample(JobDetailExample example);

    int deleteByExample(JobDetailExample example);

    int deleteByPrimaryKey(Integer jobId);

    int insert(JobDetail record);

    int insertSelective(JobDetail record);

    List<JobDetail> selectByExample(JobDetailExample example);

    JobDetail selectByPrimaryKey(Integer jobId);

    int updateByExampleSelective(@Param("record") JobDetail record, @Param("example") JobDetailExample example);

    int updateByExample(@Param("record") JobDetail record, @Param("example") JobDetailExample example);

    int updateByPrimaryKeySelective(JobDetail record);

    int updateByPrimaryKey(JobDetail record);


    /**
     * 关联查询job与trigger详情
     * @return
     */
    List<JobAndTriggerVo> queryJobAndTriggerDetails();
}