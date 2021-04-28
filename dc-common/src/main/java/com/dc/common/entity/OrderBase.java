package com.dc.common.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderBase implements Serializable {
    private Long objId;

    private String name;

    private String cellPhone;

    private Integer age;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }
}