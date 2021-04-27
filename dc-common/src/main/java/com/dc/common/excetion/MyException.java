package com.dc.common.excetion;

import java.util.Map;

/**
 * @PACKAGE_NAME: com.one.demo.common
 * @NAME: MyException
 * @USER: 25738
 * @DATE: 2021/3/3
 **/
public class MyException extends RuntimeException{
    private String msg;
    private int code;
    private String url;
    private Map<String,Object> map;


    public MyException() {
    }

    public MyException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public MyException(String msg, int code, String url, Map<String, Object> map) {
        this.msg = msg;
        this.code = code;
        this.url = url;
        this.map = map;
    }

    public String getMsg() {
        return msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "MyException{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", map=" + map +
                '}';
    }
}
