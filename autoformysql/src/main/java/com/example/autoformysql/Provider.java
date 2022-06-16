//package com.example.autoformysql;
//
//import lombok.*;
//
//import javax.persistence.*;
//import java.util.Date;
//
///**
// * @author yanglin
// * @date 2020/7/16 14:18
// */
//@Entity
//@Table(name = "t_dftc_tcjk_um_student_info")
//@Data
//@org.hibernate.annotations.Table(appliesTo = "t_dftc_tcjk_um_student_info",comment = "学生信息表")
//public class Provider {
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
//     * 健康appid
//     */
//    @Column(nullable = false,columnDefinition = "bigint(20) comment '健康appid'")
//    private String userId;
//
//    /**
//     * 学号
//     */
//    @Column(nullable = false,columnDefinition = "varchar(100) comment '学号'")
//    private String studentNum;
//
//    /**
//     * 是否激活
//     */
//    @Column(nullable = false,columnDefinition = "int(11) default 0 comment '是否激活0激活1锁定'")
//    private Integer status;
//
//    /**
//     * 机构id
//     */
//    @Column(nullable = false,columnDefinition = "bigint(20) comment '机构id'")
//    private String orgId;
//
//    /**
//     * 座机
//     */
//    @Column(nullable = false,columnDefinition = "bigint(20) comment '监护人id'")
//    private Long guardianId;
//
//    /**
//     * 所在地
//     */
//    @Column(nullable = false,columnDefinition = "varchar(100) comment '监护人姓名'")
//    private String guardianName;
//
//    /**
//     * 合作开始时间
//     */
//    @Column(nullable = false,columnDefinition = "bigint(20) comment '家庭id'")
//    private Long familyId;
//}
