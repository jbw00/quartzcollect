package com.hamusuta.quartzcollect.servic.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hamusuta.quartzcollect.dao.JobDetailMapper;
import com.hamusuta.quartzcollect.modle.JobDetail;
import com.hamusuta.quartzcollect.modle.JobDetailExample;
import com.hamusuta.quartzcollect.vo.JobAndTriggerVo;
import com.hamusuta.quartzcollect.servic.JobService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hamusuta
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDetailMapper jobDetailMapper;

    @Override
    public List<JobDetail> getUsefulJobDetail() {
        JobDetailExample jobDetailExample = new JobDetailExample();
        JobDetailExample.Criteria criteria = jobDetailExample.createCriteria();
        //查询处于激活状态的job
        criteria.andJobStatusEqualTo(1);
        List<JobDetail> jobDetails = jobDetailMapper.selectByExample(jobDetailExample);
        return jobDetails;
    }

    @Override
    public PageInfo<JobAndTriggerVo> getJobAndTriggerDetails(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTriggerVo> list = jobDetailMapper.queryJobAndTriggerDetails();
        PageInfo<JobAndTriggerVo> page = new PageInfo(list);
        return page;
    }

}
