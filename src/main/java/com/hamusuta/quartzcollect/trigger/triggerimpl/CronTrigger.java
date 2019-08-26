package com.hamusuta.quartzcollect.trigger.triggerimpl;

import com.hamusuta.quartzcollect.trigger.BaseTrigger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * cron表达式触发器
 * @author hamusuta
 */
@Component
public class CronTrigger implements BaseTrigger {

    /**
     * 入参顺序固定
     * @param parms: triggerName triggerGroup cron
     * @return
     */
    @Override
    public Trigger triggerBuilder(String... parms) {
        //解析入参
        ArrayList parmsList = new ArrayList(Arrays.asList(parms));
        String triggerName = String.valueOf(parmsList.get(0));
        String triggerGroup = String.valueOf(parmsList.get(1));
        String cronExpression = String.valueOf(parmsList.get(2));
        //cron构建
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        //触发器构建
        org.quartz.CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroup)
                .withSchedule(scheduleBuilder).build();

        return trigger;
    }

}
