package com.hamusuta.quartzcollect.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * job工具类
 * @author Bowen
 */
public class JobUtil {

    /**
     * 解析job_metrics字段
     * @return
     */
    public static List<Integer> getMetrics(String metrics){
        String[] split = metrics.split(",");
        List<Integer> result = new ArrayList(Arrays.asList(split));
        return result;
    }

}
