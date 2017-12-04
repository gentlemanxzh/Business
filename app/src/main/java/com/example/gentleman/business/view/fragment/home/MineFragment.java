package com.example.gentleman.business.view.fragment.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gentleman.business.R;

/**
 * 我的Fragment
 */
public class MineFragment extends Fragment {


    public MineFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine_layout, container, false);
    }

}
