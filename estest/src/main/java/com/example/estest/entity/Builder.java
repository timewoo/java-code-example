package com.example.estest.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yanglin
 * @date 2020/7/2 11:37
 */
@Document(indexName = "builder")
@Data
public class Builder {

    @Id
    private String id;

    @Field(type = FieldType.Text,searchAnalyzer = "ik_max_word",analyzer = "ik_max_word")
    private String builderName;

    @Field(type = FieldType.Keyword)
    private String remark;

    @Field(index = false,type = FieldType.Keyword)
    private String email;

    @Field(type = FieldType.Integer)
    private Integer buildNum;

    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date buildDate;

    @Field(type = FieldType.Double)
    private Double integral;

}
