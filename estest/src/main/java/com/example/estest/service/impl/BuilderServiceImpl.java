package com.example.estest.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.estest.entity.Builder;
import com.example.estest.repository.BuilderRepository;
import com.example.estest.service.BuilderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
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

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public void save(Builder builder) {
        builderRepository.save(builder);
    }

    @Override
    public void saveByRest(Builder builder) {
        elasticsearchRestTemplate.save(builder);
    }

    @Override
    public void saveByClient(Builder builder) {
        IndexRequest indexRequest = new IndexRequest("builder");
        indexRequest.id();
        String s = JSON.toJSONString(builder);
        indexRequest.source(s, XContentType.JSON);
        // 同步方法
        try {
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if (!indexResponse.status().equals(RestStatus.CREATED)) {
                throw new Exception("新增失败：" + indexResponse.status().getStatus());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void batchByClient(List<Builder> builders) {
        BulkRequest bulkRequest = new BulkRequest("builder");
        builders.forEach(builder -> {
            IndexRequest indexRequest = new IndexRequest("builder");
            indexRequest.id();
            String s = JSON.toJSONString(builder);
            indexRequest.source(s, XContentType.JSON);
            bulkRequest.add(indexRequest);
        });
        try {
            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            if (bulkResponse.hasFailures()){
                for (BulkItemResponse bulkItemResponse:bulkResponse){
                    if (bulkItemResponse.isFailed()){
                        BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                        System.out.println(failure.toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveByFile(String str) {
        IndexRequest indexRequest = new IndexRequest("jsontest");
        indexRequest.id();
//        String s = JSON.toJSONString(builder);
        indexRequest.source(str, XContentType.JSON);
        // 同步方法
        try {
            IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            if (!indexResponse.status().equals(RestStatus.CREATED)) {
                throw new Exception("新增失败：" + indexResponse.status().getStatus());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("builderName", name)).build();
        log.info(query.getQuery().toString());
        SearchHits<Builder> search = elasticsearchRestTemplate.search(query, Builder.class);
        Thread.sleep(1000);
        return search.getSearchHits().stream().map(SearchHit::getContent).collect(Collectors.toList());
    }

    @Override
    public Flux<Builder> selectByNameReactor(String name) {
        NativeSearchQuery query = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery("builderName", name)).build();
        log.info(query.getQuery().toString());
        Flux<SearchHit<Builder>> search = reactiveElasticsearchTemplate.search(query, Builder.class);
        return search.map(SearchHit::getContent);
    }

    @Override
    public List<Builder> selectByNameClient(String name) throws IOException {
        SearchRequest searchRequest = new SearchRequest("builder");
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("builderName", name).fuzziness(Fuzziness.AUTO);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder).sort(new FieldSortBuilder("buildDate").order(SortOrder.ASC));
        String[] includeFields = new String[]{"id", "builderName"};
        searchSourceBuilder.fetchSource(includeFields, new String[]{});
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field builderName = new HighlightBuilder.Field("builderName");
        builderName.highlighterType("unified");
        highlightBuilder.field(builderName);
        searchSourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<Builder> builderList = new ArrayList<>();
        if (searchResponse.status().equals(RestStatus.OK)) {
            searchResponse.getHits().forEach(hit -> {
                Builder builder = JSON.parseObject(hit.getSourceAsString(), Builder.class);
                builder.setId(hit.getId());
                builderList.add(builder);
            });
        }
        return builderList;
    }

}
