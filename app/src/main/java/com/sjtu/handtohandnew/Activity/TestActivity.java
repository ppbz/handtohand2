package com.sjtu.handtohandnew.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.os.Bundle;

import com.hyphenate.chat.EMClient;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sjtu.handtohandnew.Base.BaseActivity;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.Base.Model;
import com.sjtu.handtohandnew.Bean.UserInfoBean;
import com.sjtu.handtohandnew.R;

public class TestActivity extends BaseActivity implements BaseInterface {
    /*public TestActivity testAct;*/
    @ViewInject(R.id.test01)
    public static LinearLayout test01;

    private void toMainOrToLogin(){
     /*   new Thread(){
            public void run(){

            }
        }.start();*/
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //判断是否登录过
                if(EMClient.getInstance().isLoggedInBefore()){
                    //登录过
                    //获取当前登录用户的信息
                    UserInfoBean account = Model.getInstance().getUserAccountDao().getAccountByHxID(EMClient.getInstance().getCurrentUser());
                    if (account == null){
                        //跳转到登录页面
                        Intent intent = new Intent(TestActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else {
                        //登录成功后的方法
                        Model.getInstance().loginSuccess(account);

                        //跳转到主页面
                        Intent intent = new Intent(TestActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    //跳转到主页面
                    Intent intent = new Intent(TestActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else {
                    //没登录过
                    //跳转到登录页面
                    Intent intent = new Intent(TestActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        InitView();
        InitData();
        InitViewOper();

    }

    @Override
    public void InitView() {
        ViewUtils.inject(this);
        /*testAct = this;*/
        toMainOrToLogin();
    }

    @Override
    public void InitData() {

    }

    @Override
    public void InitViewOper() {

    }
    @OnClick(R.id.button)
    private void testClick(View view){
        startAct(HomeActivity.class);
    }

}
