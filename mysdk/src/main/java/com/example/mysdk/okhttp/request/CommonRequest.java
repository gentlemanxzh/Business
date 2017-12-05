package com.example.mysdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * @author gentleman
 * @function okHttp的封装
 * Created by gentleman on 2017/12/5.
 */

public class CommonRequest {

    /**
     * 返回一个封装好的post的Request
     *
     * @param url    URL地址
     * @param params 请求参数
     * @return Request
     */
    public static Request createPostRequest(String url, RequestParams params) {

        FormBody.Builder mFormBodyBuild = new FormBody.Builder();

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                //将请求遍历添加到我们的请求构建中
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }

        FormBody mFormBody = mFormBodyBuild.build();

        return new Request.Builder().url(url).post(mFormBody).build();

    }

    /**
     * 返回一个封装好的get的Request
     *
     * @param url    URL地址
     * @param params 请求参数
     * @return Request
     */
    public static Request createGetRequest(String url, RequestParams params) {

        StringBuilder stringBuilder = new StringBuilder(url).append("?");

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                stringBuilder.append(entry.getKey()).append("=")
                        .append(entry.getValue()).append("&");

            }
        }

        return new Request.Builder().url(stringBuilder.substring(0,stringBuilder.length()-1))
                .get().build();
    }


}
