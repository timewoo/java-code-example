package com.example.estest.controller;

import com.example.estest.entity.Builder;
import com.example.estest.service.BuilderService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * @author yanglin
 * @date 2020/7/2 14:26
 */
@RequestMapping("builder")
@RestController
public class BuilderController {

    @Resource
    private BuilderService builderService;

    @PostMapping("save")
    public void save(@RequestBody Builder builder){
        builderService.save(builder);
    }

    @GetMapping("selectById")
    public Builder selectById(@RequestParam String id){
        return builderService.select(id);
    }

    @GetMapping("selectByName")
    public List<Builder> selectByName(@RequestParam String name){
        return builderService.selectByName(name);
    }

    @GetMapping("selectByIk")
    public List<Builder> selectByIk(@RequestParam String name){
        return builderService.selectByIk(name);
    }

    @GetMapping("selectByRest")
    public List<Builder> selectByRest(@RequestParam String name){
        System.out.println("list start");
        List<Builder> builders = builderService.selectByNameRest(name);
        System.out.println("list end");
        return builders;
    }

    @GetMapping("selectByReactor")
    public Flux<Builder> selectByReactor(@RequestParam String name){
        System.out.println("flux start");
        Flux<Builder> builderFlux = builderService.selectByNameReactor(name);
        System.out.println("flux end");
        return builderFlux;
    }

    @GetMapping(value = "/times", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> times(){
        return Flux.interval(Duration.ofSeconds(1)).map(l->new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
