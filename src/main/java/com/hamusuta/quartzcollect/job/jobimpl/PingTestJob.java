package com.hamusuta.quartzcollect.job.jobimpl;

import com.hamusuta.quartzcollect.job.BaseJob;
import com.hamusuta.quartzcollect.util.DialingUtil;
import com.hamusuta.quartzcollect.util.JobUtil;
import com.hamusuta.quartzcollect.util.PushDataUtil;
import com.hamusuta.quartzcollect.util.TimeUtil;
import com.hamusuta.quartzcollect.vo.FalconVo;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author hamusuta
 */
@Component
public class PingTestJob extends BaseJob {

    //初始化组别
    static {
        JOB_GROUP = JOB_GROUP_HTTP;
    }

    @Autowired
    private PushDataUtil pushDataUtil;
    @Value("job.http.baidu")
    private String url;

    @Override
    protected HashMap<String, Object> getResult(JobDetail detail) {
        HashMap<String, Object> resultMap = new HashMap(2);

        //TODO-bw 实现httpjob获取值相关操作 非必传参数如请求地址请在配置文件中配置
        Integer integer = DialingUtil.dialingTest(url);
        resultMap.put("value", integer);
        return resultMap;
    }

    @Override
    protected void analysisResult(JobDetail detail, HashMap resultMap) {
        String job_metric = String.valueOf(detail.getJobDataMap().get("JOB_METRIC"));
        List<Integer> metricsId = JobUtil.getMetrics(job_metric);
        //TODO-bw 实现解析返回值逻辑 metric、endpoint由monitor 提供
        String metric = "1.2.3";
        String endpoint = "test";
        //TODO-bw step由数据库提供
        step = 300;

        //TODO-bw tags由于多变，自定义constant
        //String tags =null;

        //TODO-bw value自定义解析
        Double value = Double.valueOf(String.valueOf(resultMap.get("value")));

        Date pushDate = TimeUtil.getPushDate();

        //TODO-bw 实现falconvo构建及推送
        FalconVo falconVo = pushDataUtil.voConstructor(pushDate, metric, value, tags, step, endpoint);
        List<FalconVo> pushList = new ArrayList(1);
        pushList.add(falconVo);
        pushDataUtil.pushToFalcon(pushList);
    }
}
