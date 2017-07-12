package com.mxp.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mxp on 2017/7/12.
 */
public class Msg {
    //自定义状态码
    private int code;
    //提示信息
    private  String msg;
    //数据存储
    private Map<String,Object> map = new HashMap<>();

    public static Msg success(){
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理成功");
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
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

    public Msg add(String key,Object value){
        this.map.put(key,value);
        return this;
    }
}
