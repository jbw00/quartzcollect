package com.hamusuta.quartzcollect.servic.serviceimpl;

import com.hamusuta.quartzcollect.dao.TriggerDetailMapper;
import com.hamusuta.quartzcollect.modle.TriggerDetail;
import com.hamusuta.quartzcollect.servic.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriggerServiceImpl implements TriggerService {

    @Autowired
    private TriggerDetailMapper triggerDetailMapper;

    @Override
    public List<TriggerDetail> getTriggerDetail() {
        return null;
    }
}
