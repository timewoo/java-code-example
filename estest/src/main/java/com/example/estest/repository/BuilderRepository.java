package com.example.estest.repository;

import com.example.estest.entity.Builder;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yanglin
 * @date 2020/7/2 14:23
 */
@Repository
public interface BuilderRepository extends ElasticsearchRepository<Builder, String> {

    List<Builder> getAllByBuilderNameLike(String name);

    @Query("{\"match\": {\n" +
            "      \"builderName\": \"?0\"\n" +
            "    }}")
    List<Builder> getByIk(String name);
}
