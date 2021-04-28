package com.dc.order.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.dc.common.entity.OrderBase;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Classname Order
 * @Description 描述
 * @Date 2021/3/25 15:35
 * @Author 刁闯
 */
@TableName(value = "test_order")
@Data
public class Order  {

    private Long objId;

    private String name;

    private String cellPhone;

    private Integer age;

    private Date createTime;

    private Date updateTime;
}
