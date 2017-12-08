package com.example.gentleman.business.view.fragment.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gentleman.business.R;
import com.example.gentleman.business.network.http.RequestCenter;
import com.example.mysdk.okhttp.listener.DisposeDataListener;

/**
 * 主页的Fragment
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommanData();
    }

    private void requestRecommanData() {
        RequestCenter.requestRecommandData(new DisposeDataListener() {
            @Override
            public void OnSuccess(Object responseObj) {
                Log.e("TAG","OnSuccess:"+responseObj.toString());
            }

            @Override
            public void OnFailure(Object reasonObj) {
                Log.e("TAG","OnFailure:"+reasonObj.toString());

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_layout, container, false);
    }

}
