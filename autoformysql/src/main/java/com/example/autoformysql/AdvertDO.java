//package com.example.autoformysql;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * @author yanglin
// * @date 2020/7/16 14:18
// */
//@Entity
//@Table(name = "t_dftc_tcjk_dm_advert")
//@Data
//@org.hibernate.annotations.Table(appliesTo = "t_dftc_tcjk_dm_advert",comment = "广告表")
//public class AdvertDO {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//
//    @Column(nullable = false,columnDefinition = "datetime(0) comment '创建时间'")
//    private Date createTime;
//
//    @Column(nullable = false,columnDefinition = "datetime(0) comment '更新时间'")
//    private Date updateTime;
//
//    @Column(nullable = false,columnDefinition = "int(11) default 0 comment '是否删除'")
//    private Integer isDelete;
//
//    /**
//     * 广告位编码
//     */
//    @Column(nullable = false,columnDefinition = "bigint(20) comment '广告位Id'")
//    private Long adSpaceId;
//
//    /**
//     * 广告图片
//     */
//    @Column(nullable = false,columnDefinition = "varchar(256) comment '广告图片'")
//    private String img;
//
//    /**
//     * 广告名称
//     */
//    @Column(nullable = false,columnDefinition = "varchar(256) comment '广告名称'")
//    private Integer name;
//
//    /**
//     * 开始时间
//     */
//    @Column(nullable = false,columnDefinition = "datetime(0) comment '开始时间'")
//    private Date startTime;
//
//    /**
//     * 结束时间
//     */
//    @Column(nullable = false,columnDefinition = "datetime(0) comment '结束时间'")
//    private Date endTime;
//
//    /**
//     * 跳转类型
//     */
//    @Column(nullable = false,columnDefinition = "int(4) comment '跳转类型1商城2圈子3直播4广播5爱心社6内容7专家8医护9H5地址'")
//    private Integer jumpType;
//
//    /**
//     * 链接对象
//     */
//    @Column(nullable = false,columnDefinition = "varchar(256) comment '链接对象'")
//    private String jumpAddress;
//
//    /**
//     * 付费类型
//     */
//    @Column(nullable = false,columnDefinition = "int(1) comment '付费类型0免费1收费'")
//    private Integer type;
//
//    /**
//     * 广告排序
//     */
//    @Column(nullable = false,columnDefinition = "int(4) comment '广告排序'")
//    private Integer sort;
//
//    /**
//     * 广告状态0停用1启用
//     */
//    @Column(nullable = false,columnDefinition = "int(1) comment '广告状态0停用1启用'")
//    private Integer status;
//}
