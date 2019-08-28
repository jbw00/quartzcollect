package com.hamusuta.quartzcollect.servic.serviceimpl;

import com.hamusuta.quartzcollect.dao.JobDetailMapper;
import com.hamusuta.quartzcollect.modle.JobDetail;
import com.hamusuta.quartzcollect.modle.JobDetailExample;
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
}
