package com.hamusuta.quartzcollect.servic;

import com.hamusuta.quartzcollect.modle.TriggerDetail;

/**
 * @author hamusuta
 */
public interface TriggerService {

    /**
     * 根据triggerid获取TriggerDetail
     * @param triggerId
     * @return
     */
    TriggerDetail getTriggerDetailById(Integer triggerId);

}
