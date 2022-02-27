package com.alibaba.csp.sentinel.dashboard.config;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.convert.DegradeRuleConvert;
import com.alibaba.csp.sentinel.dashboard.rule.convert.FlowRuleConvert;
import com.alibaba.csp.sentinel.dashboard.rule.convert.ParamFlowRuleConvert;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Properties;

/**
 * @Author youxiaobin
 * @Description //TODO
 * @CreateTime 2021/11/8 17:57
 **/
@Configuration
public class NacosConfig {

    @Value("${sentinel.datasource.nacos.server-addr:localhost:8848}")
    private String serverAddr;

    @Value("${sentinel.datasource.nacos.namespace:}")
    private String namespace;

    @Value("${sentinel.datasource.nacos.enable:false}")
    private boolean enable;

    @Bean
    public FlowRuleConvert flowRuleEntityEncoder() {
        return new FlowRuleConvert();
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }

    @Bean
    public ParamFlowRuleConvert paramFlowRuleEntityEncoder() {
        return new ParamFlowRuleConvert();
    }

    @Bean
    public Converter<String, List<ParamFlowRuleEntity>> paramFlowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, ParamFlowRuleEntity.class);
    }



    @Bean
    public DegradeRuleConvert degradeRuleEntityEncoder() {
        return new DegradeRuleConvert();
    }

    @Bean
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSON.parseArray(s, DegradeRuleEntity.class);
    }

    @Bean
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE,namespace);
        try {
            Class<?> driverImplClass = Class.forName("com.alibaba.nacos.client.config.NacosConfigService");
            Constructor constructor = driverImplClass.getConstructor(Properties.class);
            ConfigService vendorImpl = (ConfigService) constructor.newInstance(properties);
            return vendorImpl;
        } catch (Throwable e) {
            throw new NacosException(NacosException.CLIENT_INVALID_PARAM, e);
        }
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
