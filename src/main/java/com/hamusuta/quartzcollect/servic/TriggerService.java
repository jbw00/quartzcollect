package com.hamusuta.quartzcollect.servic;

import com.hamusuta.quartzcollect.modle.TriggerDetail;

import java.util.List;

/**
 * @author hamusuta
 */
public interface TriggerService {

    /**
     * 获取TriggerDetail
     * @return
     */
    List<TriggerDetail> getTriggerDetail();

}
