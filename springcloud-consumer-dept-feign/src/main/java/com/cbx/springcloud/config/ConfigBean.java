package com.cbx.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {//@Configuration -- spring applicationContext.xml

    //配置负载均衡实现RestTemplate
    //IRule
    //RoundRobinRule轮询
    //RandomRule随机
    //RetryRule：会先按照轮询获取服务，如果服务获取失败，则会在指定的时间内重试
    //AvailabilityFilteringRule:先过滤掉，跳匝，访问崩溃的服务，对剩下的进行轮询
    @Bean
    @LoadBalanced//Ribbon
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}
