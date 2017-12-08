package com.example.gentleman.business.network.http;

import com.example.mysdk.okhttp.CommonOkHttpClient;
import com.example.mysdk.okhttp.listener.DisposeDataHandle;
import com.example.mysdk.okhttp.listener.DisposeDataListener;
import com.example.mysdk.okhttp.request.CommonRequest;
import com.example.mysdk.okhttp.request.RequestParams;
import com.example.mysdk.okhttp.response.CommonJsonCallback;

/**
 *
 *
 * Created by gentleman on 2017/12/6.
 */

public class HttpConstants {

    private static final String ROOT_URL = "http://192.168.43.85:8080";

    /**
     * 首页产品请求接口
     */
    public static String HOME_RECOMMAND = ROOT_URL + "/product/home_data.json";


}
