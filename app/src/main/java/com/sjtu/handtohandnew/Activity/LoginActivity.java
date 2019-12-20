package com.sjtu.handtohandnew.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnChildClick;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sjtu.handtohandnew.Base.BaseActivity;
import com.sjtu.handtohandnew.Base.BaseInterface;
import com.sjtu.handtohandnew.Base.Model;
import com.sjtu.handtohandnew.Bean.UserInfoBean;
import com.sjtu.handtohandnew.R;

public class LoginActivity extends BaseActivity implements BaseInterface {

    private EditText login_name;
    private EditText login_password;
    private Button login_register;
    private Button login_login;

    @ViewInject(R.id.login_LinearLayout)
    public static LinearLayout loginLinearLayout;

    @OnClick(R.id.login_register)
    private void register(View view){
        //获取输入的用户名密码
        String registerName = login_name.getText().toString();
        String registerPassword = login_password.getText().toString();

        //校验输入的用户名密码
        if (TextUtils.isEmpty(registerName)|| TextUtils.isEmpty(registerPassword)){
            Toast.makeText(LoginActivity.this,"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        //去服务器注册账号
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //去环信服务器注册账号
                    EMClient.getInstance().createAccount(registerName,registerPassword);

                    //更新页面显示
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (final HyphenateException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this,"注册失败"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @OnClick(R.id.login_login)
    private void loginLogin(View view){
        //获取输入的用户名和密码
        String loginName = login_name.getText().toString();
        String loginPassword = login_password.getText().toString();

        //对输入的名称密码校验
        if (TextUtils.isEmpty(loginName)|| TextUtils.isEmpty(loginPassword)){
            Toast.makeText(LoginActivity.this,"输入的用户名或密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        //登录逻辑处理
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //去环信服务器登录
                EMClient.getInstance().login(loginName, loginPassword, new EMCallBack() {
                    //登陆成功的处理
                    @Override
                    public void onSuccess() {
                        //对模型层数据的处理
                        Model.getInstance().loginSuccess(new UserInfoBean(loginName));

                        //保存用户账号信息到本地数据库
                        Model.getInstance().getUserAccountDao().addAccount(new UserInfoBean(loginName));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //提示登陆成功
                                Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();

                                //跳转到主页面
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);

                                finish();
                            }
                        });

                    }

                    //登录失败的处理
                    @Override
                    public void onError(int i, final String s) {
                        //提示登陆失败
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this,"登录失败"+s,Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    //登陆过程中的处理
                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });
    }

    @OnClick(R.id.login_name)
    private void loginName(View view){

    }

    @OnClick(R.id.login_password)
    private void loginPassword(View view){

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitView();
        InitData();
        InitViewOper();
    }

    @Override
    public void InitView() {
        ViewUtils.inject(this);
        login_name = findViewById(R.id.login_name);
        login_password = findViewById(R.id.login_password);
        login_register = findViewById(R.id.login_register);
        login_login = findViewById(R.id.login_login);
    }

    @Override
    public void InitData() {

    }

    @Override
    public void InitViewOper() {

    }
}
