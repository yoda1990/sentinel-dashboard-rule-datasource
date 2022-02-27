package com.alibaba.csp.sentinel.dashboard.rule.convert;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yoda1990
 * @Description //TODO
 * @CreateTime 2021/11/8 18:07
 **/
@Component
public class FlowRuleConvert implements Converter<List<FlowRuleEntity>, String> {

    @Override
    public String convert(List<FlowRuleEntity> flowRuleEntities) {
        if(flowRuleEntities==null){
            return null;
        }
        List<FlowRule> flowRules = new ArrayList<>();
        for (FlowRuleEntity entity : flowRuleEntities) {
            FlowRule rule = new FlowRule();
            rule.setLimitApp(entity.getLimitApp());
            rule.setResource(entity.getResource());
            if(entity.getGmtCreate()!=null){
                rule.setGrade(entity.getGrade());
            }
            if(entity.getCount()!=null){
                rule.setCount(entity.getCount());
            }
            if(entity.getStrategy()!=null){
                rule.setStrategy(entity.getStrategy());
            }
            rule.setRefResource(entity.getRefResource());
            if(entity.getControlBehavior()!=null){
                rule.setControlBehavior(entity.getControlBehavior());
            }
            if(entity.getWarmUpPeriodSec()!=null){
                rule.setWarmUpPeriodSec(entity.getWarmUpPeriodSec());
            }
            if(entity.getMaxQueueingTimeMs()!=null){
                rule.setMaxQueueingTimeMs(entity.getMaxQueueingTimeMs());
            }
            rule.setClusterMode(entity.isClusterMode());
            rule.setClusterConfig(entity.getClusterConfig());

            flowRules.add(rule);
        }
        return JSON.toJSONString(flowRules,true);
    }
}
