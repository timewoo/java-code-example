package com.example.estest.controller;

import com.example.estest.entity.Builder;
import com.example.estest.service.BuilderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
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

    @PostMapping("batchByClient")
    public void  batchByClient(){
        Builder builder = new Builder();
        builder.setBuildDate(DateUtils.parseDate("2020-07-16 16:36:00"));
        builder.setBuilderName("demoData");
        builder.setBuildNum(1);
        builder.setEmail("demoData");
        builder.setIntegral(1.0);
        builder.setRemark("demoData");
        List<Builder> builders = new ArrayList<>();
        for (int i = 0; i < 14000; i++) {
            int index = i+1;
            builder.setIntegral(builder.getIntegral()+index);
            builder.setBuilderName(builder.getBuilderName()+index);
            builder.setBuildNum(builder.getBuildNum()+index);
            builders.add(builder);
        }
        builderService.batchByClient(builders);
    }

    @PostMapping("saveByFile")
    public void saveByFile(@RequestBody MultipartFile file){
        String str = "";
        try (Reader read =new InputStreamReader(file.getInputStream(),"UTF-8")){
            BufferedReader bufferedReader = new BufferedReader(read);
            String readStr = null;
            while ((readStr = bufferedReader.readLine())!=null){
                str +=readStr;
            }
        }catch (IOException e){
            log.info(e.getStackTrace().toString());
        }
        builderService.saveByFile(str);
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
