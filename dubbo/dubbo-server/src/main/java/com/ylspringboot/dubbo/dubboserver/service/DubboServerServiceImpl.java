package com.ylspringboot.dubbo.dubboserver.service;
import org.apache.dubbo.config.annotation.Service;
import rpc.DubboDemoService;

/**
 * @author yanglin
 * @date 2020/1/8
 */
@Service(version = "1.0.0")
public class DubboServerServiceImpl implements DubboDemoService {


    @Override
    public String testConnection() {
        return "aaaa";
    }
}
