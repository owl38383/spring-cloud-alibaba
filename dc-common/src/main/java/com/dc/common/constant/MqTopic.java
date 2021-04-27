package com.dc.common.constant;

/**
 * @Classname MqTopic
 * @Description 描述
 * @Date 2021/3/8 16:41
 * @Author 刁闯
 */

public enum MqTopic {
    PAY("pay"),
    ORDER("pay"),
    MESSAGE("message"),
    ;

    MqTopic(String topic) {
        this.topic = topic;
    }

    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
