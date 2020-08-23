package com.example.estest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchTypeMapper;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;

import javax.annotation.Resource;

/**
 * @author yanglin
 * @date 2020/7/2 16:03
 */
@Configuration
public class esConfig {

    @Resource
    private SimpleElasticsearchMappingContext simpleElasticsearchMappingContext;


    @Bean
    public MappingElasticsearchConverter mappingElasticsearchConverter(){
        MappingElasticsearchConverter converter = new MappingElasticsearchConverter(simpleElasticsearchMappingContext);
        ElasticsearchTypeMapper elasticsearchTypeMapper = ElasticsearchTypeMapper.create(simpleElasticsearchMappingContext);
        return converter;
    }
}
