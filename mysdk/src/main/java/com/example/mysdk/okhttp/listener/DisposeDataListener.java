package com.example.mysdk.okhttp.listener;

/**
 * 自定义的回调监听
 * Created by gentleman on 2017/12/6.
 */

public interface DisposeDataListener {


    /**
     * 请求成功时返回
     * @param responseObj 返回的数据
     */
    public void OnSuccess(Object responseObj);


    /**
     * 请求失败的时候返回
     * @param reasonObj 返回的错误信息
     */
    public void OnFailure(Object reasonObj);


}
