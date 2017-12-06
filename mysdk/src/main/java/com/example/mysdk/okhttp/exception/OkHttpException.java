package com.example.mysdk.okhttp.exception;

/**
 * 自定义的异常类
 * Created by gentleman on 2017/12/6.
 */

public class OkHttpException extends Exception {
    private static final long serialVersionUID = 1l;

    //服务器返回的异常码
    private int ecode;

    //服务器返回的异常信息
    private Object emsg;

    public OkHttpException(int ecode,Object emsg){
        this.ecode = ecode;
        this.emsg = emsg;

    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }
}
