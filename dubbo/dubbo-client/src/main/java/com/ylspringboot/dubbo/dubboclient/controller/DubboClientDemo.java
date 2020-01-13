package com.ylspringboot.dubbo.dubboclient.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rpc.DubboDemoService;


/**
 * @author yanglin
 * @date 2020/1/8
 */
@RestController
@RequestMapping("dubboClient")
public class DubboClientDemo {

    @Reference(version = "1.0.0")
    private DubboDemoService demoService;

    @GetMapping("testConnection")
    public String testConnection(){
        return demoService.testConnection();
    }
}
