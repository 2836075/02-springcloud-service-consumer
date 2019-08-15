package com.lee.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration //等价于spring applicationContext.xml配置文件
public class BeanConfig {

    /**
     *  @Bean 等价于：
     *      <bean id="restTemplate" class="org.springframework.web.client.RestTemplate"/>
     * @return
     */
    @LoadBalanced //ribbon提供的负载均衡
    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

    /**
     * 覆盖掉原来riddon默认的轮询负载均衡策略
     *
     * @return
     */
    @Bean
    public IRule iRule(){
       // return new RandomRule();//采用随机的负载均衡策略
        return new RetryRule();//采用重试的负载均衡策略
    }
}
