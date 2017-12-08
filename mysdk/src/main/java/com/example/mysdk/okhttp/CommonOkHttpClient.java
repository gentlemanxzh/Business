package com.example.mysdk.okhttp;

import android.util.Log;

import com.example.mysdk.okhttp.https.HttpsUtils;
import com.example.mysdk.okhttp.listener.DisposeDataHandle;
import com.example.mysdk.okhttp.response.CommonJsonCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author gentleman
 * @function 请求的发送，请求参数的配置，https的支持
 * Created by gentleman on 2017/12/5.
 */

public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
        //创建okHttp的构建者
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //设置超时时间
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);
        //设置重定向
        okHttpBuilder.followRedirects(true);
        //设置https支持
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        okHttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());

        //生成我们的client对象
        mOkHttpClient = okHttpBuilder.build();
    }

    /**
     * 用于发送具体的Http/Https请求
     * @param request 请求
     * @param commCallback callback
     * @return Call
     */
    public static Call sendRequest(Request request, CommonJsonCallback commCallback){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commCallback);

        return call;

    }

    /**
     * 通过构造好的Request,Callback去发送请求
     *
     * @param request
     * @param handle
     */
    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }
}
