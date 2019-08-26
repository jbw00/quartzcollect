package com.hamusuta.quartzcollect.util;

import com.hamusuta.quartzcollect.vo.FalconVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Bowen
 */
@Component
public class PushDataUtil {

    @Value("${pushdata.username}")
    private String userName;
    @Value("${pushdata.password}")
    private String passWord;

    private Logger logger = LoggerFactory.getLogger(PushDataUtil.class);

    private static final Integer FIVE_MIN_STEP = 300;

    /**
     * 此处应调用push data client进行数据统一推送
     *
     * @param pushList
     */
    public void pushToFalcon(List pushList) {
        //TODO-bw 数据推送
        logger.info("数据推送完成");
    }

    /**
     * 默认步长五分钟,可自定义步长
     * @param now
     * @param metric
     * @param value
     * @param tags
     * @param step
     * @param endpoint
     * @return
     */
    public FalconVo voConstructor(Date now, String metric, Double value, String tags, Integer step, String endpoint) {
        String dateToStr = TimeUtil.dateToStr(now, TimeUtil.DEFAULT_DATETIME_FORMAT);
        FalconVo falconVo = new FalconVo();
        falconVo.setCounterType("GAUGE");
        falconVo.setEndpoint(endpoint);
        falconVo.setMetric(metric);
        if(StringUtils.isEmpty(step)){
            falconVo.setStep(FIVE_MIN_STEP);
        }else {
            falconVo.setStep(step);
        }
        falconVo.setTimestamp(dateToStr);
        falconVo.setValue(value);
        falconVo.setTags(tags);
        return falconVo;
    }
}