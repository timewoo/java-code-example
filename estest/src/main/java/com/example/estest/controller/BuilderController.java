package com.example.estest.controller;

import com.example.estest.entity.Builder;
import com.example.estest.service.BuilderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.io.IOException;
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
@Slf4j
public class BuilderController {

    @Resource
    private BuilderService builderService;

    @PostMapping("save")
    public void save(@RequestBody Builder builder) {
        builderService.save(builder);
    }

    @PostMapping("saveByRest")
    public void saveByRest(@RequestBody Builder builder) {
        builderService.saveByRest(builder);
    }

    @PostMapping("saveByClient")
    public void saveByClient(@RequestBody Builder builder){
        builderService.saveByClient(builder);
    }

    @GetMapping("selectById")
    public Builder selectById(@RequestParam String id) {
        return builderService.select(id);
    }

    @GetMapping("selectByName")
    public List<Builder> selectByName(@RequestParam String name) {
        return builderService.selectByName(name);
    }

    @GetMapping("selectByIk")
    public List<Builder> selectByIk(@RequestParam String name) {
        return builderService.selectByIk(name);
    }

    @GetMapping("selectByRest")
    public List<Builder> selectByRest(@RequestParam String name) {
        log.info("list start");
        List<Builder> builders = null;
        try {
            builders = builderService.selectByNameRest(name);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
            Thread.currentThread().interrupt();
        }
        log.info("list end");
        return builders;
    }

    @GetMapping("selectByReactor")
    public Flux<Builder> selectByReactor(@RequestParam String name) {
        log.info("flux start");
        Flux<Builder> builderFlux = builderService.selectByNameReactor(name);
        log.info("flux end");
        return builderFlux;
    }

    @GetMapping("selectByNameClient")
    public List<Builder> selectByNameClient(@RequestParam String name) throws IOException {
        return builderService.selectByNameClient(name);
    }

    @GetMapping(value = "/times", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> times() {
        return Flux.interval(Duration.ofSeconds(1)).map(l -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
