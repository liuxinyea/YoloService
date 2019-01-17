package com.example.demo.bean;

import java.util.Map;

public class ResponseMap {
    private int code;
    private String msg;
    private Map res;
    public ResponseMap() {
    }
    public ResponseMap(int code, String msg, Map res) {
        this.code = code;
        this.msg = msg;
        this.res = res;
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

    public Map getRes() {
        return res;
    }

    public void setRes(Map res) {
        this.res = res;
    }
}
