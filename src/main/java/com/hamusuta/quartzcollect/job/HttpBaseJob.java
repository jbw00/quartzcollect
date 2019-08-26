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
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author hamusuta
 */
@Component
public abstract class HttpBaseJob implements Job {

    private static String JOB_GROUP = "http";
    private static Logger logger = LoggerFactory.getLogger(HttpBaseJob.class);
    protected Integer step = 300;
    protected String tags = null;

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
    protected abstract HashMap<String, Object> getResult(JobDetail detail);

    /**
     * 根据重写方法解析告警/指标内容并推送
     * @param detail
     * @param resultMap
     */
    protected abstract void analysisResult(JobDetail detail, HashMap resultMap);

}
