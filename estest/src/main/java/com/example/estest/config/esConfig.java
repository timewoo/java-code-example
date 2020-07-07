package com.example.estest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchDateConverter;
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentEntity;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentProperty;
import org.springframework.data.elasticsearch.core.mapping.ElasticsearchPersistentPropertyConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;
import org.springframework.data.mapping.context.MappingContext;

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
        return converter;
    }
}
