package com.dc.common.entity.base;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserBase implements Serializable {
    @TableId
    private Long id;
    private String username;//用户名唯一
    private String cellPhone;//手机号唯一
    private String nickName;//昵称唯一
    private String headImgUrl;//用户头像Url
    private String email;//邮箱
    private Integer status;//状态: 1有效，2无效
    private Date createTime;//创建时间
    private String password;//密码
    private String salt;//密码扰码
    private String payPassword;//支付密码
    private String paySalt;//支付密码扰码
}
