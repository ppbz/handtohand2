package com.sjtu.handtohandnew.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sjtu.handtohandnew.Activity.TestActivity;
import com.sjtu.handtohandnew.Base.BaseFragment;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.R;

public class SelectFragment extends BaseFragment implements BaseInterface,View.OnClickListener {
    private Button mBtnSFind;
    private Button mBtnSSend;
    private OrderFragment orderFragment;
    private SendFragment sendFragment;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        orderFragment = new OrderFragment();
        sendFragment = new SendFragment();
        mBtnSFind = view.findViewById(R.id.btn_sfind);
        mBtnSSend = view.findViewById(R.id.btn_ssend);
        mBtnSFind.setOnClickListener(new OnClick());
        mBtnSSend.setOnClickListener(new OnClick());
    }

    @Override
    protected void init() {
        InitView();
        InitData();
        InitViewOper();
    }

    @Override
    protected View initContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_select,null);
    }

    @Override
    public void InitView() {
    }


    /*@OnClick(R.id.fragment_order_but)
    public void onRegClick(View v) {
        startAct(LoginActivity.class);
    }*/

    @Override
    public void InitData() {

    }

    @Override
    public void InitViewOper() {

    }

    @Override
    public void onClick(View v) {

    }

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_sfind:
                    if (orderFragment == null){
                        orderFragment = new OrderFragment();
                    }
                    /*Fragment fragment01 = getFragmentManager().findFragmentByTag("a");*/
                    /*if (fragment01 != null){
                        getFragmentManager().beginTransaction().hide(fragment01).add(R.id.act_home_viewpager,orderFragment).addToBackStack(null).commitAllowingStateLoss();
                    } else {*/
                    getFragmentManager().beginTransaction().replace(R.id.act_home_viewpager, orderFragment).addToBackStack(null).commitAllowingStateLoss();
                    /*}*/
                    break;

                case R.id.btn_ssend:
                    if(sendFragment == null){
                        sendFragment = new SendFragment();
                    }
                    /*Fragment fragment02 = getFragmentManager().findFragmentByTag("a");*/
                    /*if (fragment02 != null){
                        getFragmentManager().beginTransaction().hide(fragment02).add(R.id.act_home_viewpager,sendFragment).addToBackStack(null).commitAllowingStateLoss();
                    } else {*/
                    getFragmentManager().beginTransaction().replace(R.id.act_home_viewpager,sendFragment).addToBackStack(null).commitAllowingStateLoss();
                    /*}*/
                    break;

                /*case R.id.btn_stest:
                    Intent intent = new Intent(activity, Test02Activity.class);
                    startActivity(intent);
                    break;*/
            }
        }
    }


}

