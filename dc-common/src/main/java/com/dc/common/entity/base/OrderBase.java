package com.dc.common.entity.base;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderBase implements Serializable {

    @TableId
    private Long objId;

    private String name;

    private String cellPhone;

    private Integer age;

    private Date createTime;

    private Date updateTime;
}