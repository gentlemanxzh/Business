package com.example.gentleman.business.network.http;

import com.example.gentleman.business.module.recommand.BaseRecommandModel;
import com.example.mysdk.okhttp.CommonOkHttpClient;
import com.example.mysdk.okhttp.listener.DisposeDataHandle;
import com.example.mysdk.okhttp.listener.DisposeDataListener;
import com.example.mysdk.okhttp.request.CommonRequest;
import com.example.mysdk.okhttp.request.RequestParams;
import com.example.mysdk.okhttp.response.CommonJsonCallback;
import com.example.mysdk.util.ResponseEntityToModule;

/**
 * @author gentleman
 * @function 用于数据请求的方法类
 * Created by gentleman on 2017/12/6.
 */

public class RequestCenter {

    //post请求的具体操作
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.sendRequest(CommonRequest.
                createGetRequest(url, params), new CommonJsonCallback(new DisposeDataHandle(listener,clazz)));
    }

    //根据参数发送所有的post请求
    public static void requestRecommandData(DisposeDataListener listener) {

        RequestCenter.postRequest(HttpConstants.HOME_RECOMMAND, null, listener, ResponseEntityToModule.class);
    }
}
