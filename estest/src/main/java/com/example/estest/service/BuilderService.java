package com.example.estest.service;

import com.example.estest.entity.Builder;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

/**
 * @author yanglin
 * @date 2020/7/2 14:24
 */
public interface BuilderService {

    void save(Builder builder);

    void saveByRest(Builder builder);

    public void saveByClient(Builder builder);

    Builder select(String id);

    List<Builder> selectByName(String name);

    List<Builder> selectByIk(String name);

    List<Builder> selectByNameRest (String name) throws InterruptedException;

    Flux<Builder> selectByNameReactor(String name);

    List<Builder> selectByNameClient(String name) throws IOException;
}
