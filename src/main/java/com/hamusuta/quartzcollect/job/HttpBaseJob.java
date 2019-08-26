package com.hamusuta.quartzcollect.job;

import com.alibaba.fastjson.JSONArray;
import com.hamusuta.quartzcollect.util.JobUtil;
import com.hamusuta.quartzcollect.util.PushDataUtil;
import com.hamusuta.quartzcollect.util.TimeUtil;
import com.hamusuta.quartzcollect.vo.FalconVo;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author hamusuta
 */
public class HttpBaseJob implements Job {

    private static String JOB_GROUP = "http";
    private static Logger logger = LoggerFactory.getLogger(HttpBaseJob.class);

    @Autowired
    private PushDataUtil pushDataUtil;

    /**
     * Http定时任务实现方法
     * @param context 传递参数实体
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException{
        JobDetail detail = context.getJobDetail();
        String name = detail.getKey().getName();
        String group = detail.getKey().getGroup();
        if (!JOB_GROUP.equals(group)){throw new RuntimeException("执行任务与job组未对应，请检查实现的job类！");}
        logger.info("==========================>Http任务：{}，组别{},任务开始！", name, group);
        //获取结果值
        HashMap<String, Object> result = getResult(detail);
        //解析并推送
        analysisResult(detail, result);
        logger.info("<==========================Http任务结束");
    }

    /**
     * 根据重写方法获取告告警/指标内容
     * @param detail
     * @return
     */
    public HashMap<String, Object> getResult(JobDetail detail){
        HashMap<String, Object> resultMap = new HashMap();

        //TODO-bw 实现httpjob获取值相关操作 非必传参数如请求地址请在配置文件中配置
        //String url = String.valueOf(detail.getJobDataMap().get("URL"));
        return resultMap;
    }

    /**
     * 根据重写方法解析告警/指标内容并推送
     * @param detail
     * @param resultMap
     */
    public void analysisResult(JobDetail detail, HashMap resultMap){
        String job_metric = String.valueOf(detail.getJobDataMap().get("JOB_METRIC"));
        List<Integer> metricsId = JobUtil.getMetrics(job_metric);
        //TODO-bw 实现解析返回值逻辑
        String metric = null;
        String endpoint = null;
        Integer step = 300;
        String tags =null;
        Double value = 0.0;
        Date pushDate = TimeUtil.getPushDate();

        //TODO-bw 实现falconvo构建及推送
        /* 推送示例
        FalconVo falconVo = pushDataUtil.voConstructor(pushDate, metric, value, tags, step, endpoint);
        List<FalconVo> pushList = new ArrayList();
        pushList.add(falconVo);
        pushDataUtil.pushToFalcon(pushList);
        */
    }

}
