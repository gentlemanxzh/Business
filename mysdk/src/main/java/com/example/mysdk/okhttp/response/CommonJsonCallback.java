package com.example.mysdk.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.example.mysdk.okhttp.exception.OkHttpException;
import com.example.mysdk.okhttp.listener.DisposeDataHandle;
import com.example.mysdk.okhttp.listener.DisposeDataListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author gentleman
 * @function 专门处理Json的回调处理
 * Created by gentleman on 2017/12/6.
 */

public class CommonJsonCallback implements Callback {


    //与服务器返回的一个对应关系
    protected final String RESULT_CODE = "ecode";
    protected final int RESULT_CODE_VALUE = 0;
    protected final String ERROR_MSG = "emsg";
    protected final String EMPTY_MSG = "";

    protected final int NETWORK_ERROR = -1;//网络错误
    protected final int JSON_ERROR = -2;//数据错误
    protected final int OTHER_ERROR = -1;//其他错误

    //自定义事件监听
    private DisposeDataListener mListener;
    //Handler,用来事件的转发
    private Handler mDeliveryHandler;
    //实体类对象
    private Class<?> mClass;


    public CommonJsonCallback(DisposeDataHandle handle) {
        this.mListener = handle.mListener;
        this.mDeliveryHandler = new Handler(Looper.getMainLooper());
        this.mClass = handle.mClass;
    }


    /**
     * 请求失败的处理
     * @param call Call
     * @param e IOException
     */
    @Override
    public void onFailure(Call call, final IOException e) {
        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.OnFailure(new OkHttpException(NETWORK_ERROR,e));
            }
        });
    }

    /**
     * 请求成功
     * @param call Call
     * @param response Response
     * @throws IOException
     */
    @Override
    public void onResponse(Call call, Response response) throws IOException {

        //将返回的response转换为string类型
        final String result = response.body().string();

        mDeliveryHandler.post(new Runnable() {
            @Override
            public void run() {

                handleResponse(result);
            }
        });

    }

    private void handleResponse(Object responseObj) {
        if (responseObj==null&&responseObj.toString().trim().equals("")){
            mListener.OnFailure(new OkHttpException(JSON_ERROR,EMPTY_MSG));
            return;
        }
        try {
            JSONObject result = new JSONObject(responseObj.toString());
            //判断返回的responseObj返回的字段是否有RESULT_CODE
            if (result.has(RESULT_CODE)){
                if (result.getInt(RESULT_CODE)==RESULT_CODE_VALUE){
                    if (mClass==null){
                        //返回的数据不需要转换为实体对象
                        mListener.OnSuccess(result);
                    }else {
                        //TODO 需要我们转换为实体对象
                        mListener.OnSuccess(result);
                    }

                }else {
                    //服务器异常
                    mListener.OnFailure(new OkHttpException(OTHER_ERROR,result.getInt(RESULT_CODE)));
                }

            }

        }catch (Exception e){
            mListener.OnFailure(new OkHttpException(OTHER_ERROR,e.getMessage()));
            e.printStackTrace();
        }
    }
}
