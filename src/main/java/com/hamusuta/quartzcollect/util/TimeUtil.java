package com.hamusuta.quartzcollect.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * core中包含该方法
 * @author Bowen
 */
public class TimeUtil {

    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final Integer FIVE_MIN = 5;

    /**
     * date转指定时间格式
     * @param date
     * @param format
     * @return
     */
    public static String dateToStr(Date date, String format) {
        if (null == date) {
            return "";
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        }
    }

    /**
     * 获取推送时间(以5分钟整倍数为准)
     * @return
     */
    public static Date getPushDate(){
        Calendar now = Calendar.getInstance();
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) - now.get(Calendar.MINUTE) % FIVE_MIN);
        return now.getTime();
    }

}
