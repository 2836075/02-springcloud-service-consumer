package com.lee.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@EnableEurekaClient //开启Eureka客户端支持
public class WebController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/web/hello")
    public String hello(){
        //调用远程的springcloud服务提供的服务
        //return restTemplate.getForEntity("http://localhost:8080/service/hello",String.class).getBody();
        return restTemplate.getForEntity("http://01-SPRINGCLOUD-SERVICE-PROVIDER/service/hello",String.class).getBody();
    }

    @RequestMapping("/web/hystrix")
    @HystrixCommand(fallbackMethod = "error", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    }) //熔断器回调 error方法,超时时间是1.5秒
    public String hystrix(){
        //调用远程的springcloud服务提供的服务
        //return restTemplate.getForEntity("http://localhost:8080/service/hello",String.class).getBody();
        return restTemplate.getForEntity("http://01-SPRINGCLOUD-SERVICE-PROVIDER/service/hello",String.class).getBody();
    }
    @RequestMapping("/web/hystrix1")
    @HystrixCommand(fallbackMethod = "error", ignoreExceptions = Exception.class,commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1500")
    }) //熔断器回调 error方法,超时时间是1.5秒
    public String hystrix1(){
        //调用远程的springcloud服务提供的服务
        //return restTemplate.getForEntity("http://localhost:8080/service/hello",String.class).getBody();
        return restTemplate.getForEntity("http://01-SPRINGCLOUD-SERVICE-PROVIDER/service/hello",String.class).getBody();
    }

    public String error(Throwable throwable){
        System.out.println(throwable.getMessage());
        //访问远程服务失败的处理
        return "error";
    }
}
