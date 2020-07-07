package com.example.estest.service.impl;

import com.example.estest.entity.Builder;
import com.example.estest.repository.BuilderRepository;
import com.example.estest.service.BuilderService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author yanglin
 * @date 2020/7/2 14:25
 */
@Service
@Slf4j
public class BuilderServiceImpl implements BuilderService {

    @Resource
    private BuilderRepository builderRepository;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    @Override
    public void save(Builder builder) {
        builderRepository.save(builder);
    }

    @Override
    public Builder select(String id) {
        Optional<Builder> builderOptional = builderRepository.findById(id);
        return builderOptional.orElse(null);
    }

    @Override
    public List<Builder> selectByName(String name) {
        return builderRepository.getAllByBuilderNameLike(name);
    }

    @Override
    public List<Builder> selectByIk(String name) {
        return builderRepository.getByIk(name);
    }

    @Override
    public List<Builder> selectByNameRest(String name) throws InterruptedException {
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("builderName",name)).build();
        log.info(query.getQuery().toString());
        SearchHits<Builder> search = elasticsearchRestTemplate.search(query, Builder.class);
        Thread.sleep(1000);
        return search.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public Flux<Builder> selectByNameReactor(String name) {
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("builderName",name)).build();
        log.info(query.getQuery().toString());
        Flux<SearchHit<Builder>> search = reactiveElasticsearchTemplate.search(query, Builder.class);
        return search.map(SearchHit::getContent);
    }

}
