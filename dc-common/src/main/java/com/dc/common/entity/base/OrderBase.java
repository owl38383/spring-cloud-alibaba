package com.dc.common.entity.base;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "test_order")
@Data
public class OrderBase implements Serializable {

    private Long objId;

    private String name;

    private String cellPhone;

    private Integer age;

    private Date createTime;

    private Date updateTime;
}