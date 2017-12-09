package com.example.gentleman.business.view.fragment.home;


import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gentleman.business.R;
import com.example.gentleman.business.network.http.RequestCenter;
import com.example.gentleman.business.view.fragment.BaseFragment;
import com.example.mysdk.okhttp.listener.DisposeDataListener;

/**
 * 主页的Fragment
 */
public class HomeFragment extends BaseFragment {

    /**
     * UI
     */
    private View mContentView;
    private TextView mQRCodeView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;




    public HomeFragment() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommanData();
    }

    private void initView() {
        mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
        mCategoryView = (TextView)mContentView.findViewById(R.id.category_view);
        mSearchView = (TextView)mContentView.findViewById(R.id.search_view);
        mLoadingView = (ImageView)mContentView.findViewById(R.id.loading_view);

        //设置mLoadingView加载动画
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }

    private void requestRecommanData() {
        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void OnSuccess(Object responseObj) {
                Log.e("TAG", "OnSuccess:" + responseObj.toString());
            }

            @Override
            public void OnFailure(Object reasonObj) {
                Log.e("TAG", "OnFailure:" + reasonObj.toString());

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mContentView;
    }

}
