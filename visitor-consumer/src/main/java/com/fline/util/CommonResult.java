package com.fline.util;

import org.apache.poi.ss.formula.functions.T;

public class CommonResult {
    private Integer state;
    private String msg;
    private Object data;

    public CommonResult() {
        this.state=200;
        this.msg="成功";
    }

    public CommonResult(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public CommonResult(Integer state, String msg, Object data) {
        this.state = state;
        this.msg = msg;
        this.data = data;
    }
    public  CommonResult(String msg, Object data){
        this.msg = msg;
        this.data = data;
    }

    public <S extends T> CommonResult(S save) {
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}