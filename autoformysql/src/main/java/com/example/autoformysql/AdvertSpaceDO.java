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
//@Table(name = "t_dftc_tcjk_dm_advert_space")
//@Data
//@org.hibernate.annotations.Table(appliesTo = "t_dftc_tcjk_dm_advert_space",comment = "广告位表")
//public class AdvertSpaceDO {
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
//    @Column(nullable = false,columnDefinition = "varchar(256) comment '广告位编码'")
//    private String code;
//
//    /**
//     * 广告位名称
//     */
//    @Column(nullable = false,columnDefinition = "varchar(256) comment '广告位名称'")
//    private String name;
//
//    /**
//     * 所属终端
//     */
//    @Column(nullable = false,columnDefinition = "int(4) comment '所属终端'")
//    private Integer terminal;
//
//    /**
//     * 广告位类型
//     */
//    @Column(nullable = false,columnDefinition = "int(4) comment '广告位类型'")
//    private Integer type;
//
//    /**
//     * 轮播张数
//     */
//    @Column(nullable = false,columnDefinition = "int(4) comment '监护人id'")
//    private Integer number;
//
//    /**
//     * 图片高度
//     */
//    @Column(nullable = false,columnDefinition = "int(4) comment '监护人姓名'")
//    private Integer high;
//
//    /**
//     * 图片宽度
//     */
//    @Column(nullable = false,columnDefinition = "bigint(20) comment '家庭id'")
//    private Integer weight;
//
//    /**
//     * 广告位状态0停用1启用
//     */
//    @Column(nullable = false,columnDefinition = "int(1) default 0 comment '广告位状态0停用1启用'")
//    private Integer status;
//}
