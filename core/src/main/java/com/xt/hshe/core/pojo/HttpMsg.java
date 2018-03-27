package com.xt.hshe.core.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * HTTP 响应对象
 *
 * @author Shawn Chen
 */
@Getter
@Setter
@ToString
public class HttpMsg<T> {
    private int code;
    private String msg;
    private T data;
    private String timestamp;
    //成功提示
    public HttpMsg(String msg){
        this.code = 1;
        this.msg = msg;
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }
    public HttpMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }
    public HttpMsg(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }
}
