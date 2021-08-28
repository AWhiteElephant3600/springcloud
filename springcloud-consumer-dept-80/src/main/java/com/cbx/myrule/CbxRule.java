package com.cbx.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CbxRule {

    @Bean
    public IRule myIRule(){
        return new CbxRandomRule();//默认是轮询，现在我们自定义为CbxRandomRule
    }
}
