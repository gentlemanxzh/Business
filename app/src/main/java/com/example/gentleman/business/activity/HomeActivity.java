package com.example.gentleman.business.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gentleman.business.R;
import com.example.gentleman.business.activity.base.BaseActivity;
import com.example.gentleman.business.view.fragment.home.HomeFragment;
import com.example.gentleman.business.view.fragment.home.MessageFragment;
import com.example.gentleman.business.view.fragment.home.MineFragment;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    /**
     * Fragment
     */
    private FragmentManager fm;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private MineFragment mMineFragment;
    private Fragment mCurrent;

    private RelativeLayout mHomeLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMineLayout;

    private TextView mHomeView;
    private TextView mMessageView;
    private TextView mMineView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
        initView();

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        mHomeFragment = new HomeFragment();
        ft.replace(R.id.content_layout, mHomeFragment);
        ft.commit();

    }

    private void initView() {
        mHomeLayout = (RelativeLayout) findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);
        mMessageLayout = (RelativeLayout) findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMineLayout =(RelativeLayout) findViewById(R.id.mine_layout_view);
        mMineLayout.setOnClickListener(this);

        mHomeView = (TextView)findViewById(R.id.home_image_view);
        mMessageView = (TextView)findViewById(R.id.message_image_view);
        mMineView = (TextView)findViewById(R.id.mine_image_view);

        mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
    }

    /**
     * 用于隐藏Fragment
     * @param fragment 需要隐藏的Fragment
     * @param fragmentTransaction 事务
     */
    private void hideFragment(Fragment fragment,FragmentTransaction fragmentTransaction) {
        if (fragment!=null) {
            fragmentTransaction.hide(fragment);
        }
    }

    @Override
    public void onClick(View view) {

        FragmentTransaction ft = fm.beginTransaction();

        switch (view.getId()){
            case R.id.home_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mMessageFragment,ft);
                hideFragment(mMineFragment,ft);
                if (mHomeFragment==null){
                    mHomeFragment = new HomeFragment();
                    ft.add(R.id.content_layout, mHomeFragment);
                }else {
                    mCurrent = mHomeFragment;
                    ft.show(mHomeFragment);
                }

                break;

            case R.id.message_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                hideFragment(mHomeFragment,ft);
                hideFragment(mMineFragment,ft);
                if (mMessageFragment==null){
                    mMessageFragment = new MessageFragment();
                    ft.add(R.id.content_layout, mMessageFragment);
                }else {
                    mCurrent = mMessageFragment;
                    ft.show(mMessageFragment);
                }

                break;

            case R.id.mine_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);

                hideFragment(mHomeFragment,ft);
                hideFragment(mMessageFragment,ft);
                if (mMineFragment==null){
                    mMineFragment = new MineFragment();
                    ft.add(R.id.content_layout, mMineFragment);
                }else {
                    mCurrent = mMineFragment;
                    ft.show(mMineFragment);
                }

                break;
        }

        ft.commit();

    }


}
