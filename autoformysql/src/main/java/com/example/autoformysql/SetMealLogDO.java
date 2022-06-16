package com.example.autoformysql;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yanglin
 * @date 2021/1/14 17:01
 */
@Entity
@Table(name = "t_dftc_tcjk_im_set_meal_log")
@Data
@org.hibernate.annotations.Table(appliesTo = "t_dftc_tcjk_im_set_meal_log", comment = "套餐日志")
public class SetMealLogDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, columnDefinition = "datetime(0) comment '创建时间'")
    private Date createTime;

    @Column(nullable = false, columnDefinition = "datetime(0) comment '更新时间'")
    private Date updateTime;

    @Column(nullable = false, columnDefinition = "int(11) default 0 comment '是否删除'")
    private Integer isDelete;

    @Column(nullable = false,columnDefinition = "bigint(20) comment '套餐Id'")
    private Long setMealId;

    @Column(nullable = false, columnDefinition = "int(11) comment '操作类型1新增2修改'")
    private Integer type;

    @Column(nullable = false,columnDefinition = "bigint(20) comment '操作人Id'")
    private Long userId;

}
