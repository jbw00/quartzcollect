package com.hamusuta.quartzcollect.dao;

import com.hamusuta.quartzcollect.modle.TriggerDetail;
import com.hamusuta.quartzcollect.modle.TriggerDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TriggerDetailMapper {
    long countByExample(TriggerDetailExample example);

    int deleteByExample(TriggerDetailExample example);

    int deleteByPrimaryKey(Integer triggerId);

    int insert(TriggerDetail record);

    int insertSelective(TriggerDetail record);

    List<TriggerDetail> selectByExample(TriggerDetailExample example);

    TriggerDetail selectByPrimaryKey(Integer triggerId);

    int updateByExampleSelective(@Param("record") TriggerDetail record, @Param("example") TriggerDetailExample example);

    int updateByExample(@Param("record") TriggerDetail record, @Param("example") TriggerDetailExample example);

    int updateByPrimaryKeySelective(TriggerDetail record);

    int updateByPrimaryKey(TriggerDetail record);
}