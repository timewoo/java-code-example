//package com.example.autoformysql;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * @author yanglin
// * @date 2021/1/14 17:01
// */
//@Entity
//@Table(name = "t_dftc_tcjk_um_feedback")
//@Data
//@org.hibernate.annotations.Table(appliesTo = "t_dftc_tcjk_um_feedback", comment = "医护意见反馈")
//public class FeedbackDO {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Column(nullable = false, columnDefinition = "datetime(0) comment '创建时间'")
//    private Date createTime;
//
//    @Column(nullable = false, columnDefinition = "datetime(0) comment '更新时间'")
//    private Date updateTime;
//
//    @Column(nullable = false, columnDefinition = "int(11) default 0 comment '是否删除'")
//    private Integer isDelete;
//
//    @Column(nullable = false, columnDefinition = "bigint(20) comment '用户Id'")
//    private Long userId;
//
//    @Column(columnDefinition = "text comment '反馈内容'")
//    private String content;
//
//    @Column( columnDefinition = "text comment '图片'")
//    private String img;
//
//    @Column(columnDefinition = "varchar(256) comment '联系方式'")
//    private String contact;
//}
