package com.lee.springcloud.controller;

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

}
