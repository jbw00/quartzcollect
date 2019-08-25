package com.hamusuta.quartzcollect.trigger;

import org.quartz.Trigger;

/**
 * @author hamusuta
 */
public interface BaseTrigger {

    /**
     * 基础触发器接口，无论是simpletrigger、crontrigger、自定义trigger都需要实现该方法
     * @param triggerName
     * @param triggerGroup
     * @return
     */
    Trigger triggerBuilder (String triggerName, String triggerGroup);

}
